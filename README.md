# Limit 2

Versión aparte de [Limit](https://github.com/nelavpeluffo/Limit) para probar funciones nuevas. Se instala como otra app (`com.appcel.v2`), así que puede convivir con Limit en el mismo teléfono.

App para Android que limita a **15 minutos por día**:

- los **Reels de video** de Instagram (la pestaña Reels y los que abrís desde la lupa, el feed o mensajes) y la **lupa** (Explorar), que comparten los mismos 15 minutos. Todo lo que abrís desde la lupa cuenta (también fotos y carruseles); en el resto de Instagram, las fotos y carruseles que aparecen en el reproductor no cuentan.
- la pestaña **Para ti** de X (Twitter)

El resto de Instagram y X (feed, historias, mensajes, la pestaña *Siguiendo*) se puede usar sin límite.

## Cómo funciona

- Mientras estás en Reels o en Para ti, arriba de la pantalla aparece una pastillita con el tiempo que te queda (se puede ocultar).
- Cuando falta 1 minuto, te avisa.
- Al llegar a 15 minutos te saca: en Instagram te lleva al Inicio (si insistís, a la pantalla de inicio del teléfono) y en X te pasa a *Siguiendo* aunque las pestañas estén escondidas (deslizando como harías vos), sin cerrar la app.
- Además, el botón de Reels queda tapado con un candado lila hasta la medianoche, y el de la lupa con un botón violeta con lupa que abre directo el buscador (sin la grilla de publicaciones). Buscar cuentas no cuenta tiempo. En X, con el límite cumplido, *Para ti* no se llega a ver: se tapa con la pantalla violeta del reloj mientras te pasa a *Siguiendo*, y después aparece un aviso violeta que dice "Siguiendo".
- El contador se reinicia a la medianoche.

## Instalarla

1. Descargá **Limit-v2.apk** desde [la última versión](../../releases/latest).
2. Abrí el archivo. Si Android pregunta, permití instalar apps desde esa fuente (Chrome o Archivos).
3. Abrí Limit 2 y seguí los pasos de la pantalla para activarla en **Accesibilidad**.
4. Si Android dice **«Configuración restringida»**: en Limit tocá «Abrir info de la app» → menú ⋮ arriba a la derecha → «Permitir configuración restringida». Después volvé a activarla en Accesibilidad.

## Actualizar

Con [Obtainium](https://github.com/ImranR98/Obtainium) (agregando `https://github.com/nelavpeluffo/Limit-v2`) avisa y actualiza con un toque. También se puede desde Limit 2: **Descargar la última versión**, abrir el archivo y tocar **Actualizar**. No hace falta volver a activarla.

## Privacidad

La app no tiene permiso de internet. Solo busca los botones de las pestañas (Reels en Instagram; Para ti / Siguiendo en X) para saber cuál está seleccionada, y en Instagram si está abierto el reproductor de Reels a pantalla completa (por su nombre interno y tamaño, sin mirar el video). En X puede simular un deslizamiento del dedo, solo para pasarte de Para ti a Siguiendo. De las demás apps solo sabe cuál está abierta (para sacar el candado al instante cuando salís). No recorre el resto de la pantalla, no lee publicaciones ni mensajes y no guarda nada de lo que ves. El modo diagnóstico tampoco guarda textos: solo si esos botones están seleccionados.

## Compilación

Cada cambio que se sube a `main` se compila solo con GitHub Actions y se publica en *Releases*.
