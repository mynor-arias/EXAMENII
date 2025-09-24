package org.example.game;

import org.example.pokemon.*;
import org.example.attacks.Attack;
import org.example.exceptions.InvalidChoiceException;
import org.example.exceptions.AttackMissedException;
import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private Map<String, Integer> pokedex;
    private List<BattleEvent> battleLog;
    private List<Integer> damageHistory;

    private Scanner scanner;
    private String nombreJugador;
    private Pokemon pokemonJugador;
    private Pokemon pokemonCPU;
    private Random random;

    public Game() {
        scanner = new Scanner(System.in);
        battleLog = new ArrayList<>();
        damageHistory = new ArrayList<>();
        random = new Random();

        pokedex = new HashMap<>();
        pokedex.put("Charmander", 1);
        pokedex.put("Squirtle", 2);
        pokedex.put("Bulbasaur", 3);
        pokedex.put("Pikachu", 4);
    }

    public void iniciarJuego() {
        System.out.println("Batalla Pokemon ");
        System.out.println("Bienvenido ");

        obtenerNombre();
        seleccionarPokemon();
        batalla();
        mostrarResumen();
    }

    private void obtenerNombre() {
        System.out.print("Ingrese su nombre: ");
        nombreJugador = scanner.nextLine();
        System.out.println("Hola " + nombreJugador );
    }

    private void seleccionarPokemon() {
        try {
            System.out.println("\nSelecciona a un Pokemon ");
            System.out.println("1. Charmander (Fuego)");
            System.out.println("2. Squirtle (Agua)");
            System.out.println("3. Bulbasaur (Planta)");
            System.out.println("4. Pikachu (Eléctrico)");
            System.out.print("Elige (1-4): ");

            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion < 1 || opcion > 4) {
                throw new InvalidChoiceException("Eliga un numero entre 1 y 4");
            }

            pokemonJugador = crearPokemon(opcion);
            pokemonCPU = seleccionarPokemonCPU();

            System.out.println("\n" + nombreJugador + " has elegido a " + pokemonJugador.getNombre());
            pokemonJugador.entrarBatalla();


            System.out.println("\nCPU eligio a " + pokemonCPU.getNombre());
            pokemonCPU.entrarBatalla();

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número valido");
            seleccionarPokemon();
        } catch (InvalidChoiceException e) {
            System.out.println("Error: " + e.getMessage());
            seleccionarPokemon();
        }
    }

    private Pokemon crearPokemon(int opcion) {
        switch (opcion) {
            case 1: return new Charmander();
            case 2: return new Squirtle();
            case 3: return new Bulbasaur();
            case 4: return new Pikachu();
            default: return new Charmander();
        }
    }

    private Pokemon seleccionarPokemonCPU() {
        List<Pokemon> opciones = new ArrayList<>();
        opciones.add(new Charmander());
        opciones.add(new Squirtle());
        opciones.add(new Bulbasaur());
        opciones.add(new Pikachu());

        List<Pokemon> disponibles = opciones.stream()
                .filter(p -> !p.getTipo().equals(pokemonJugador.getTipo()))
                .collect(Collectors.toList());

        return disponibles.get(random.nextInt(disponibles.size()));
    }

    private void batalla() {
        System.out.println("\nBATALLA INICIADA ");
        int turno = 1;

        while (!pokemonJugador.estaDebilitado() && !pokemonCPU.estaDebilitado()) {
            System.out.println("\nTurno " + turno + " ");
            System.out.println(pokemonJugador.toString());
            System.out.println(pokemonCPU.toString());

            turnoJugador();
            if (!pokemonCPU.estaDebilitado()) {
                turnoCPU();
            }

            turno++;
        }
    }

    private void turnoJugador() {
        try {
            System.out.println("\nTurno de " + nombreJugador);

            List<Attack> ataques = pokemonJugador.getAtaques().stream()
                    .sorted((a1, a2) -> Integer.compare(a2.getPrecision(), a1.getPrecision()))
                    .collect(Collectors.toList());

            for (int i = 0; i < ataques.size(); i++) {
                System.out.println((i + 1) + ". " + ataques.get(i));
            }

            System.out.print("Elige tu ataque (1-" + ataques.size() + "): ");
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion < 1 || opcion > ataques.size()) {
                throw new InvalidChoiceException("Eliga un ataque entre 1 y 4");
            }

            Attack ataqueElegido = ataques.get(opcion - 1);
            ejecutarAtaque(pokemonJugador, pokemonCPU, ataqueElegido, nombreJugador);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero");
            turnoJugador();
        } catch (InvalidChoiceException e) {
            System.out.println("Error: " + e.getMessage());
            turnoJugador();
        }
    }

    private void turnoCPU() {
        System.out.println("\nTurno CPU ");

        Attack ataqueElegido = pokemonCPU.getAtaques().stream()
                .max((a1, a2) -> Integer.compare(a1.getPrecision() + a1.getBasePower(),
                        a2.getPrecision() + a2.getBasePower()))
                .orElse(pokemonCPU.getAtaques().get(0));

        System.out.println("CPU usa " + ataqueElegido.getNombre());
        ejecutarAtaque(pokemonCPU, pokemonJugador, ataqueElegido, "CPU");
    }

    private void ejecutarAtaque(Pokemon atacante, Pokemon defensor, Attack ataque, String nombreAtacante) {
        try {
            int damage = ataque.ejecutar();
            defensor.recibirDamage(damage);

            System.out.println( ataque.getNombre() + " causo " + damage + " de daño");

            battleLog.add(new BattleEvent(nombreAtacante, "ataco con " + ataque.getNombre(), damage, defensor.getNombre()));
            damageHistory.add(damage);

        } catch (AttackMissedException e) {
            System.out.println(e.getMessage());
            battleLog.add(new BattleEvent(nombreAtacante, "fallo con " + ataque.getNombre(), defensor.getNombre()));
        }
    }

    private void mostrarResumen() {
        System.out.println("\n BATALLA TERMINADA ");

        if (pokemonJugador.estaDebilitado() && pokemonCPU.estaDebilitado()) {
            System.out.println(" EMPATE ");
        } else if (pokemonCPU.estaDebilitado()) {
            System.out.println(" GANASTE " + nombreJugador + " ");
        } else {
            System.out.println("CPU gano ");
        }

        System.out.println("\n Log de eventos ");
        battleLog.forEach(System.out::println);

        mostrarEstadisticas();
    }

    private void mostrarEstadisticas() {
        System.out.println("\n Estadisticas ");

        if (damageHistory.isEmpty()) {
            System.out.println("No hay datos de daño");
            return;
        }

        long totalFallos = battleLog.stream()
                .filter(evento -> evento.getAccion().contains("fallo"))
                .count();
        System.out.println("Total fallos: " + totalFallos);

        List<Integer> top3 = damageHistory.stream()
                .filter(d -> d > 0)
                .sorted(Collections.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top 3 golpes: " + top3);

        double promedio = damageHistory.stream()
                .filter(d -> d > 0)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio de daño: %.1f\n", promedio);

        Map<String, Long> conteoActores = battleLog.stream()
                .collect(Collectors.groupingBy(BattleEvent::getActor, Collectors.counting()));

        System.out.println("Acciones por actor:");
        conteoActores.forEach((actor, count) -> System.out.println(actor + ": " + count));
    }

    public static void main(String[] args) {
        Game juego = new Game();
        juego.iniciarJuego();
    }
}