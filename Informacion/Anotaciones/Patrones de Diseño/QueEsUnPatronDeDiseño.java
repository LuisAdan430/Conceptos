/*
    * Los patrones de diseño (design patterns) son soluciones habituales a
    * problemas comunes en el diseño de software. Cada patrón es como un
    * plano que se puede personalizar para resolver un problema de diseño particular de tu código.
    
    ^ En ingeniería de software, a patrón de diseño es una solución repetible general a un problema común en el diseño de software.
    ^ Un patrón de diseño no es un diseño terminado que se puede transformar directamente en código.
    ^ Es una descripción o plantilla de cómo resolver un problema que se puede utilizar en muchas situaciones diferentes.
    
    * Los patrones de diseño son soluciones habituales a problemas que ocurren con frecuencia en el diseño de software. 
    * Son como planos prefabricados que se pueden personalizar para resolver un problema de diseño recurrente en tu código.
    * No se puede elegir un patrón y copiarlo en el programa como si se tratara de funciones o bibliotecas ya preparadas. 
    * El patrón no es una porción específica de código, sino un concepto general para resolver un problema particular. 
    * Puedes seguir los detalles del patrón e implementar una solución que encaje con las realidades de tu propio programa.
    * A menudo los patrones se confunden con algoritmos porque ambos conceptos describen soluciones típicas a problemas conocidos. 
    * Mientras que un algoritmo siempre define un grupo claro de acciones para lograr un objetivo, un patrón es una descripción de más alto nivel de una solución.
    * El código del mismo patrón aplicado a dos programas distintos puede ser diferente.
     
    ^ Usos de los Patrones de Diseño
    ^ Los patrones de diseño pueden acelerar el proceso de desarrollo al proporcionar paradigmas de desarrollo probados y probados. 
    ^ El diseño efectivo del software requiere considerar problemas que pueden no ser visibles hasta más adelante en la implementación. 
    ^ y mejora la legibilidad del código para codificadores y arquitectos familiarizados con los patrones.
    ^ A menudo, las personas solo entienden cómo aplicar ciertas técnicas de diseño de software a ciertos problemas.
    ^ Estas técnicas son difíciles de aplicar a una gama más amplia de problemas.
    ^ Los patrones de diseño proporcionan soluciones generales, documentadas en un formato que no requiere detalles vinculados a un problema en particular.
    ^ Además, los patrones permiten a los desarrolladores comunicarse utilizando
    ^ nombres conocidos y bien entendidos para las interacciones de software. 
    ^ Los patrones de diseño comunes se pueden mejorar con el tiempo, haciéndolos más robustos que los diseños ad-hoc.
    
    * Una analogía de un algoritmo sería una receta de cocina: ambos cuentan con pasos claros para alcanzar una meta. 
    * Por su parte, un patrón es más similar a un plano, ya que puedes observar cómo son su resultado y sus funciones,
    * pero el orden exacto de la implementación depende de ti.
    
    * ¿En qué consiste el patrón?
    * La mayoría de los patrones se describe con mucha formalidad para que la gente pueda reproducirlos en muchos contextos.
    * Aquí tienes las secciones que suelen estar presentes en la descripción de un patrón:
    * El propósito del patrón explica brevemente el problema y la solución.
    * La motivación explica en más detalle el problema y la solución que brinda el patrón.
    * La estructura de las clases muestra cada una de las partes del patrón y el modo en que se relacionan.
    * El ejemplo de código en uno de los lenguajes de programación populares facilita la asimilación de la idea que se esconde tras el patrón.
    
    * HISTORIA DE LOS PATRONES
    * ¿Quién inventó los patrones de diseño? Esa es una buena, aunque imprecisa pregunta. 
    * Los patrones de diseño no son conceptos opacos y sofisticados, al contrario. 
    * Los patrones son soluciones habituales a problemas comunes en el diseño orientado a objetos.
    * Cuando una solución se repite una y otra vez en varios proyectos, al final alguien le pone un nombre y explica la solución en detalle. 
    * Básicamente, así es como se descubre un patrón.
    
    * ¿ Por qué debería aprender sobre patrones ? 
    * La realidad es que podrías trabajar durante años como programador sin conocer un solo patrón. Mucha gente lo hace. 
    * Incluso en ese caso, podrías estar implementando patrones sin saberlo. Así que, ¿por qué dedicar tiempo a aprenderlos?
    
    * Los patrones de diseño son un juego de herramientas de soluciones comprobadas a problemas habituales en el diseño de software.
    * Incluso aunque nunca te encuentres con estos problemas, conocer los patrones sigue siendo de utilidad, porque te enseña a resolver todo tipo de problemas 
    * utilizando principios del diseño orientado a objetos.
    
    * Los patrones de diseño definen un lenguaje común que puedes utilizar con tus compañeros de equipo para comunicaros de forma más eficiente. 
    * Podrías decir: “Oh, utiliza un singleton para eso”, y todos entenderían la idea de tu sugerencia. 
    * No habría necesidad de explicar qué es un singleton si conocen el patrón y su nombre.
    
    * Clasificación de los patrones
    * Los patrones de diseño varían en su complejidad, nivel de detalle y escala de aplicabilidad al sistema completo que se diseña. 
    * Me gusta la analogía de la construcción de carreteras: puedes hacer más segura una intersección instalando semáforos o construyendo un intercambiador 
    * completo de varios niveles con pasajes subterráneos para peatones.
    * Los patrones más básicos y de más bajo nivel suelen llamarse idioms.
    * Normalmente se aplican a un único lenguaje de programación.
    * Los patrones más universales y de más alto nivel son los patrones de arquitectura. 
    * Los desarrolladores pueden implementar estos patrones prácticamente en cualquier lenguaje. 
    * Al contrario que otros patrones, pueden utilizarse para diseñar la arquitectura de una aplicación completa.
    * Además, todos los patrones pueden clasificarse por su propósito. Este libro cubre tres grupos generales de patrones:
    * Los patrones creacionales proporcionan mecanismos de creación de objetos que incrementan la flexibilidad y la reutilización de código existente.
    * Los patrones estructurales explican cómo ensamblar objetos y clases en estructuras más grandes a la vez que se mantiene 
    * la flexibilidad y eficiencia de la estructura.
    * Los patrones de comportamiento se encargan de una comunicación efectiva y la asignación de responsabilidades entre objetos.
    
    ^ Patrones de diseño creacional
    ^ Estos patrones de diseño tienen que ver con la instanciación de clase.
    ^ Este patrón se puede dividir en patrones de creación de clase y patrones de creación de objetos.
    ^ Mientras que los patrones de creación de clases usan la herencia de manera efectiva en el proceso de instanciación,
    ^ los patrones de creación de objetos usan la delegación de manera efectiva para hacer el trabajo.
    ^ Fábrica Abstracta : Crea una instancia de varias familias de clases.
    ^ Constructor : Separa la construcción de objetos de su representación.
    ^ Método de Fábrica : Crea una instancia de varias clases derivadas.
    ^ Piscina de Objetos:  Evite la adquisición costosa y la liberación de recursos reciclando objetos que ya no están en uso
    ^ Prototipo : Una instancia totalmente inicializada para ser copiada o clonada.
    ^ Singleton : Una clase de la cual solo puede existir una sola instancia.

    


     
     
*/