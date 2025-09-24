Hola este proyecto es un juego de pokemon el cual un usuario puede jugar contra el CPU.


Se puede elegir un pokemon de 4. 

Iniciamos el programa ejecutandolo en Intellij la clase "GAME", nos pedira ingresar nuestro nombre
despues de esto podremos escoger dentro de los  pokemos los cuales son, Bulbasaur, Charmander,
Pikachu y Squirtle.

Cada uno tiene 4 poderes los cuales tienen un dano y una presion, entre mas dano hace el poder menos precision tendra.

Las carpetas estan ordenadas de la siguiente manera:

ATTACKS: 

attack: Aqui esta la clase que tomamos para crear las precesiones y el dano de los pokemones

DagameRule: interfaz sirve para definir reglas de dano

EXCEPTIONS:

AttackMissedException: Aqui esta la excepcion por si el ataque falla

InvalidChoiceException: Aqui tenemos la excepcion por si el usuario elegi una opcion incorrecta


GAME:

BattleEvent : es un registro de acciones de la batalla.

Game: Aqui esta toda la logica del programa es nuestro "MAIN"

POKEMON: 

En esta Carpeta tenemos a los 4 Pokemos y la clase Abstracta "Pokemon" para crear a los 4
