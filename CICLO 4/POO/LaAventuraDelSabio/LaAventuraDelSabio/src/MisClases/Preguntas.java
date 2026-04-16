package MisClases;

import InterfaceAbstracta.Personaje;

/**
 * La clase {@code Preguntas} administra el banco de preguntas,
 * respuestas correctas y opciones múltiples utilizadas en el juego.
 * <p>
 * Contiene:
 * <ul>
 *     <li>Un arreglo de preguntas.</li>
 *     <li>Un arreglo de respuestas correctas.</li>
 *     <li>Una matriz de opciones (A, B, C, D) por pregunta.</li>
 * </ul>
 * </p>
 * Provee métodos para obtener preguntas, respuestas y
 * mostrar una pregunta formateada con sus alternativas.
 */
public class Preguntas {

    /** Arreglo que almacena todas las preguntas del juego. */
    private String[] preguntas;

    /** Arreglo de respuestas correctas, alineado por índice con el arreglo de preguntas. */
    private String[] respuestas;

    /** Matriz de opciones múltiples (A, B, C, D) para cada pregunta. */
    private String[][] opciones;

    /**
     * Constructor que inicializa todas las preguntas, respuestas
     * y opciones del banco de preguntas.
     * <p>
     * Los datos están predefinidos dentro del constructor y no se cargan desde archivos.
     * </p>
     */
    public Preguntas() {
        preguntas = new String[]{
                "¿Cuanto es 7 + 6?",
                "¿Cuanto es la raiz cuadrada de 81?",
                "¿Cual es la capital de Peru?",
                "¿Cuanto es 5 x 12?",
                "¿Con que palabra se define una clase en Java?",
                "¿Que instrumento mide la temperatura?",
                "¿Quien fue el libertador del Peru?",
                "¿Que funcion se usa para imprimir en consola?",
                "¿Quien escribio 'Romeo y Julieta'?",
                "¿Cuanto es 2 al cubo?",
                "¿Que estudia la cinematica?",
                "¿Cuanto es 100 dividido entre 4?",
                "¿Quien fue el ultimo inca del Tahuantinsuyo?",
                "¿Que estructura se usa para repetir código mientras una condicion sea verdadera?",
                "¿Que evento inicio la Edad Moderna?",
                "¿Cual fue la capital del imperio incaico?",
                "¿Que civilización construyo Machu Picchu?",
                "¿Quien formulo las leyes del movimiento?",
                "¿Que palabra se usa para comparar cadenas de texto?",
                "¿Que velocidad tiene la luz en el vacio (en km/s)?",
                "¿Que imperio construyo el Coliseo Romano?",
                "¿Cual es la formula de la velocidad?",
                "¿Con que palabra se define un arreglo en Java?",
                "¿Que unidad se usa para medir fuerza en el SI?",
                "¿En que año se proclamo la independencia del Peru?",
                "¿En que batalla se consolido la independencia del Peru?",
                "¿Quien lidero el movimiento de independencia de la India?",
                "¿Que año comenzo la Segunda Guerra Mundial?",
                "¿Que año cayó el Imperio Romano de Occidente?",
                "¿Quien fue el emperador frances derrotado en Waterloo?"
        };

        opciones = new String[][]{
                {"a) 11", "b) 12", "c) 13", "d) 14"},
                {"a) 7", "b) 8", "c) 9", "d) 10"},
                {"a) Arequipa", "b) Trujillo", "c) Lima", "d) Cusco"},
                {"a) 50", "b) 60", "c) 55", "d) 65"},
                {"a) define", "b) class", "c) public", "d) static"},
                {"a) Barometro", "b) Termometro", "c) Higrometro", "d) Cronometro"},
                {"a) Bolivar", "b) San Martin", "c) Grau", "d) Bolognesi"},
                {"a) print()", "b) echo", "c) System.out.println", "d) write()"},
                {"a) Cervantes", "b) Lope de Vega", "c) Shakespeare", "d) Moliere"},
                {"a) 6", "b) 7", "c) 8", "d) 9"},
                {"a) Movimiento", "b) Genética", "c) Electricidad", "d) Optica"},
                {"a) 20", "b) 25", "c) 30", "d) 35"},
                {"a) Manco Capac", "b) Pachacutec", "c) Atahualpa", "d) Tupac Amaru"},
                {"a) if", "b) switch", "c) for", "d) while"},
                {"a) Guerra Civil", "b) Caida de Roma", "c) Descubrimiento de America", "d) Revolución Francesa"},
                {"a) Lima", "b) Ayacucho", "c) Cusco", "d) Arequipa"},
                {"a) Azteca", "b) Inca", "c) Maya", "d) Nazca"},
                {"a) Newton", "b) Einstein", "c) Galileo", "d) Kepler"},
                {"a) compareTo", "b) ==", "c) equals", "d) isEqual"},
                {"a) 150000", "b) 200000", "c) 250000", "d) 300000"},
                {"a) Griego", "b) Romano", "c) Egipcio", "d) Persa"},
                {"a) v=s/t", "b) v=d*t", "c) v=d/t", "d) v=s*d"},
                {"a) int", "b) list", "c) array", "d) new"},
                {"a) Pascal", "b) Newton", "c) Joule", "d) Watt"},
                {"a) 1821", "b) 1824", "c) 1810", "d) 1820"},
                {"a) Junin", "b) Ayacucho", "c) Tarapaca", "d) Pichincha"},
                {"a) Mandela", "b) Gandhi", "c) Luther King", "d) Lenin"},
                {"a) 1938", "b) 1940", "c) 1939", "d) 1941"},
                {"a) 476", "b) 500", "c) 450", "d) 400"},
                {"a) Alejandro Magno", "b) Julio Cesar", "c) Napoleon", "d) Hitler"},
        };

        respuestas = new String[]{
                "c", "c", "c", "b", "b", "b", "b", "c", "c", "c",
                "a", "b", "c", "d", "c", "c", "b", "a", "c", "d",
                "b", "c", "d", "b", "a", "b", "b", "c", "a", "c"
        };
    }

    /**
     * Obtiene el texto de una pregunta específica.
     *
     * @param nroPregunta índice de la pregunta requerida.
     * @return Devuelve la pregunta correspondiente al índice.
     */
    public String getPregunta(int nroPregunta) {
        return preguntas[nroPregunta];
    }

    /**
     * Devuelve la respuesta correcta asociada a una pregunta.
     *
     * @param nroPregunta índice de la pregunta.
     * @return Letra de la respuesta correcta (a, b, c, d).
     */
    public String getRespuesta(int nroPregunta) {
        return respuestas[nroPregunta];
    }

    /**
     * Muestra en consola la pregunta completa junto con
     * sus opciones múltiples, incluyendo información del jugador
     * que está respondiendo el turno.
     *
     * @param nroPregunta índice de la pregunta a mostrar.
     * @param jugador objeto {@link Personaje} que está respondiendo.
     * @param turno número del turno actual del juego.
     */
    public void mostrarPreguntaCompleta(int nroPregunta, Personaje jugador, int turno) {
        System.out.println("┌──────────────────────────────────┬────────┬───────┐");
        System.out.printf("│ Jugador: %-24s│ PS:%-4d│ XP:%-3d│\n",
                jugador.getNombre(), jugador.getPs(), jugador.getXp());
        System.out.println("├──────────────────────────────────┴────────┴───────┤");
        System.out.printf("│ Pregunta N°%-39s│\n", (turno + 1));
        System.out.println("├───────────────────────────────────────────────────┤");
        System.out.println(" " + getPregunta(nroPregunta));

        for (String opcion : opciones[nroPregunta]) {
            System.out.println(" " + opcion);
        }

        System.out.println("└───────────────────────────────────────────────────┘");
    }
}