package com.appcel;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Tiempo usado hoy en cada contenido, y tiempo de WhatsApp que lo habilita.
 * Cada minuto en WhatsApp habilita un minuto de Reels/TikTok/Shorts y un minuto de
 * Para ti, hasta 15 minutos por día. Se reinicia solo al cambiar el día.
 */
final class Usage {
    static final long LIMIT_MS = 15 * 60 * 1000L;

    private static final String PREFS = "usage";
    private static final String KEY_DATE = "date";
    private static final String KEY_WHATSAPP = "whatsapp";

    private final SharedPreferences prefs;

    Usage(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    private static String today() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    private void ensureToday() {
        String t = today();
        if (!t.equals(prefs.getString(KEY_DATE, ""))) {
            prefs.edit().clear().putString(KEY_DATE, t).apply();
        }
    }

    long used(Kind k) {
        ensureToday();
        return prefs.getLong(k.key, 0);
    }

    /** Tiempo habilitado hoy por el uso de WhatsApp (como mucho, 15 minutos). */
    long earned() {
        ensureToday();
        return Math.min(LIMIT_MS, prefs.getLong(KEY_WHATSAPP, 0));
    }

    /** Tiempo usado hoy en WhatsApp (sin tope). */
    long whatsapp() {
        ensureToday();
        return prefs.getLong(KEY_WHATSAPP, 0);
    }

    void addWhatsApp(long ms) {
        if (ms <= 0) return;
        ensureToday();
        prefs.edit().putLong(KEY_WHATSAPP, prefs.getLong(KEY_WHATSAPP, 0) + ms).apply();
    }

    /** Ya se habilitaron los 15 minutos del día: lo que se bloquee queda así hasta mañana. */
    boolean fullyEarned() {
        return earned() >= LIMIT_MS;
    }

    long remaining(Kind k) {
        return Math.max(0, earned() - used(k));
    }

    void add(Kind k, long ms) {
        if (ms <= 0) return;
        ensureToday();
        prefs.edit().putLong(k.key, prefs.getLong(k.key, 0) + ms).apply();
    }

    /** Devuelve true solo una vez cada vez que queda poco (para avisar una sola vez). */
    boolean markWarned(Kind k) {
        ensureToday();
        String key = "warned_" + k.key;
        if (prefs.getBoolean(key, false)) return false;
        prefs.edit().putBoolean(key, true).apply();
        return true;
    }

    /** Volvió a haber más de 1 minuto (WhatsApp habilitó más): se puede volver a avisar. */
    void rearmWarned(Kind k) {
        ensureToday();
        String key = "warned_" + k.key;
        if (prefs.getBoolean(key, false)) prefs.edit().putBoolean(key, false).apply();
    }

    static String format(long ms) {
        long s = (ms + 999) / 1000;
        return String.format(Locale.US, "%d:%02d", s / 60, s % 60);
    }
}
