# Tasks:
1. [Task01Objects](#task01objects)
    + Task02Objects-A
    + Task02Objects-B
2. [Task02Threads](#task02threads)
2. [Task03Composite](#task03composite)

# Task01Objects
### Task:

> __Овал__. Разработать классы Точка и Овал (задан двумя точками описанного прямоугольника). Создать методы и тесты: вычисления площади и периметра фигуры; состовляют ли точки овал (не лежат ли две точки на одной прямой, параллельной осям координат); пересекает ли фигура только одну из осей координат на заданное расстояние; является ли фигура овалом, кругом.

### Using in project
##### External libraries:
- `log4g2-2.11.2` - Logging debug information, warns, exception description.
- `testng-6.14.3` - Unit testing project classes.
##### Plugins:
-   `Sketch it` - Automatic generation *UML diagrams* by module, by package, by class.
-   `Plant UML` - Visualization *UML diagrams* generatred by *Sketch it*.
##### Patterns:
- `Observer`
- `Singleton`
- `Repository`
### Project architecture
![task01ObjectsUML](https://i.ibb.co/QKn3m00/task01-Objects.png)
```
by.tolkun.ellipse/
├── action/
|       └── EllipseAction.class
|               Class for calculating geometric properties of Ellipse.class.
├── entity/
|       ├── geometry/
|       |       ├── Ellipse.class
|       |       |       Class geometric figure Ellipse with fields beginPoint and endPointe.
|       |       ├── Geometry2D.class
|       |       |       Command interface for all geometric objects in a square.
|       |       ├── ObservableEllipse.class
|       |       |       Observable class for notifying its observers about updates.
|       |       └── Point2D.class
|       |               Class of geometric object `Point2D` with fields x and y.
|       ├── recorder/
|       |       └── EllipseRecorder.class
|       |               Class for storing information about of appropriate Ellipse.
|       └── AbstractEntity.class
|               Class for making common field id.
├── exception/
|       └── WrongArgumentException.class
|                Class of Exception reflection wrong input data.
├── factory/
|       ├── geometry/
|       |       ├── EllipseFactory.class
|       |       |       Class of factory for producing Ellipse.
|       |       ├── Geometric2DFactory.class
|       |       |       Abstract class of factory for producing geometric figures in a square.
|       |       ├── ObservableEllipseFactory.class
|       |       |       Class of factory for producing ObservableEllipse.
|       |       └── Point2DFactory.class
|       |               Class of factory for producing Point2D.
|       └── recorder/
|               └── EllipseRecorderBuilder.class
|                       Class for creating object of EllipseRecorder.
├── observer/
|       ├── event/
|       |       └── Geometric2DEvent.class
|       |               The class from which all event state objects shall be derived.
|       ├── ObservableFigure.class
|       |       Common interface for observable figures.
|       └── ObserverFigure.class
|               Common interface for figure observers.
├── parser/
|       └── DoubleArrayParser.class
|               Class for parsing input data (string) to double array.
├── reader/
|       └── DataReader.class
|               Class for reading text files.
├── repository/
|       ├── specification/
|       |       ├── impl/
|       |       |       ├── FindByFirstQuadrantEllipseSpecification.class
|       |       |       |       Specification to find ellipse from first quadrant.
|       |       |       ├── FindByIdEllipseSpecification.class
|       |       |       |       Specification to find ellipse with the same id.
|       |       |       ├── FindByPerimeterBetweenEllipseSpecification.class
|       |       |       |       Specification to find ellipse the perimeter in diapason.
|       |       |       ├── FindByPerimeterEllipseSpecification.class
|       |       |       |       Specification to find ellipse with the same perimeter.
|       |       |       ├── FindBySquareBetweenEllipseSpecification.class
|       |       |       |       Specification to find ellipse the square in diapason.
|       |       |       ├── FindBySquareSpecification.class
|       |       |       |       Specification to find ellipse with the same square.
|       |       |       ├── SortByBeginPointXEllipseSpecification.class
|       |       |       |       Specification to sort ellipses by x coordinate of begin point.
|       |       |       ├── SortByBeginPointYEllipseSpecification.class
|       |       |       |       Specification to sort ellipses by y coordinate of begin point.
|       |       |       ├── SortByIdEllipseSpecification.class
|       |       |       |       Specification to sort ellipses by id.
|       |       |       ├── SortByPerimeterEllipseSpecification.class
|       |       |       |       Specification to sort ellipses by perimeter.
|       |       |       └── SortBySquareEllipseSpecification.class
|       |       |               Specification to sort ellipses by square.
|       |       ├── EllipseSpecification.class
|       |       |       Interface marker to mark specification interfaces.
|       |       ├── FindEllipseSpecification.class
|       |       |       Interface to realize find query.
|       |       └── SortEllipseSpecification.class
|       |               Interface to realize sort query.
|       └── EllipseRepository.class
|               Collection for storing objects ObservableEllipse, EllipseRecorder.
└── validator/
        ├── EllipseValidator.class
        |       Class for validation on the possibility of creating Ellipse.
        ├── FileValidator.class
        |       Class for validation on the possibility of reading file.
        ├── Point2DValidator.class
        |       Class for validation on the possibility of creating Point.
        └── StringValidator.class
                Class for validation on the possibility of parsing into double array.
```


# Task02Threads
### Task:

> __Свободная касса__. В ресторане быстрого обслуживания есть несколько касс. Посетители становятся в очередь в каждую конкретную кассу. Посетитель может сделать предварительный заказ и получить его в любой кассе без очереди.

### Using in project
##### External libraries:
- `log4g2-2.11.2` - Logging debug information, warns, exception description.
##### Internal libraries:
- `java.util.concurrent` - Synchronization of threads when working with a shared resource.
- `java.util.cuncurrent.locks` - Provides a framework for locking and waiting for conditions that is distinct from built-in synchronization and monitors..
##### Plugins:
-   `Sketch it` - Automatic generation *UML diagrams* by module, by package, by class.
-   `Plant UML` - Visualization *UML diagrams* generatred by *Sketch it*.
##### Patterns:
- `Singleton`
- `Repository`
### Project architecture
![task02Threads](https://i.ibb.co/87Kvp8q/task02-Threads.png)
```
by.tolkun.cashier/
├── entity/
|       ├── RestaurantCashier.class
|       |       Class provide serving RestaurantOrder in Restaurant.
|       ├── RestaurantCheck.class
|       |       Class to store information about completed RestaurantOrder.
|       ├── RestaurantCustomer.class
|       |       Class of customer with orders for serving in Restaurant by RestaurantCashier.
|       └── RestaurantOrder.class
|               Class to store information about of RestaurantCustomer's RestaurantOrder.
├── exception/
|       ├── CashierAmountException.class
|       |       Class of Exception reflection the absence of cashiers in Restaurant.
|       └── WrongArgumetException.class
|               Class of Exception reflection wrong input data.
├── factory/
|       ├── RestaurantCashierFactory.class
|       |       Class of factory for producing RestaurantCashier.
|       ├── RestaurantCheckFactory.class
|       |       Class of factory for producing RestaurantCheck.
|       ├── RestaurantCustomerFactory.class
|       |       Class of factory for producing RestaurantCustomer.
|       └── RestaurantOrderFactory.class
|               Class of factory for producing RestaurantOrder.
├── main/
|       ├── MainGeneration.class
|       |       Class to demonstrate the work of multithreading app. Object generates automatically.
|       └── MainReading.class
|               Class to demonstrate the work of multithreading app. Object reads from file.
├── parser/
|       └── RestaurantOrderParser.class
|               Class for parsing input string to data for creating RestaurantOrder.
├── reader/
|       └── CustomerDataReader.class
|               Class for reading text files.
├── repository/
|       └── Restaurant.class
|               Class repository provides storing of objects of class RestaurantCashier.
└── validator/
        ├── FileValidator.class
        |       Class for validation on the possibility of reading file.
        ├── RestaurantOrderValidator.class
        |       Class for validation on the possibility of creation RestaurantOrder.
        └── StringValidator.class
                Class for validation on the possibility of parsing into RestaurantOrder.
```
# Task03Composite
### Task:

> Создать приложение, разбирающее текст из файла и позволяющее выполнять с текстом три различные операции:
> 1. Отсортировать обзацы по количеству предложений;
> 2. В каждом предложении отсортировать слова по длине;
> 3. Отсортировать лексемы в тексте по убыванию количества вхождений заданного символа, а в случае равенства - по алфивиту;

### Using in project
##### External libraries:
- `log4g2-2.11.2` - Logging debug information, warns, exception description.
- `testng-6.14.3` - Unit testing project classes.
##### Plugins:
-   `Sketch it` - Automatic generation *UML diagrams* by module, by package, by class.
-   `Plant UML` - Visualization *UML diagrams* generatred by *Sketch it*.
##### Patterns:
- `Composite`
- `Interpreter`
- `Chain of responsibility`
- `Visitor`
- `Strategy`
### Project architecture
![task03Composite](https://i.ibb.co/fkrLcWS/task03-Composite.png)
```
by.tolkun.infohandler/
├── action/
|       └── LexemeListSorter.class
|               Class to sort lexeme list by descending number of occurrences of a given character.
├── composite/
|       ├── strategy/
|       |       ├── SortLexemeBySymbolsStrategy.class
|       |       |       Class for sorting words by the length.
|       |       ├── SortParagraphsBySentencesStrategy.class
|       |       |       Class for sorting paragraphs by the number of sentences.
|       |       └── SortTextStrategy.class
|       |               Abstract class of sort strategy.
|       ├── visitor/
|       |       ├── impl/
|       |       |       └── LexemeVisitor.class
|       |       |               Visitor to traversal text components of composite and form lexeme list.
|       |       ├── Visitable.class
|       |       |       Interface for classes allow to visit and traversal them.
|       |       └── Visitor.class
|       |               Interface for visitor classes.
|       ├── Symbol.class
|       |       Class of leaf text component.
|       ├── TextComponent.class
|       |       Abstract class to provide work with composite.
|       ├── TextComponentType.class
|       |       Class to present type of {@code TextComponent}.
|       └── TextComposite.class
|               Composite class of text to store and work with different text components.
├── exception/
|       ├── ParserException.class
|       |       Class of {@code Exception} reflection wrong input data to parse.
|       ├── UnsupportedMethodException.class
|       |       Class of {@code Exception} reflection wrong function all.
|       └── WrongArgumentException.class
|               Class of {@code Exception} reflection wrong input data.
├── interpreter/
|       ├── AbstractBitExpression.class
|       |       Abstract class interpretable bit expressions.
|       ├── BinaryNonTerminal.class
|       |       Abstract class of not terminal operator with two operands.
|       ├── Context.class
|       |       Class to store cache values during the calculating.
|       ├── Interpreter.class
|       |       Class for interpreting (calculation) bit expression.
|       ├── NonTerminalAnd.class
|       |       Class to interpret not terminal as bit operation AND.
|       ├── NonTerminalNot.class
|       |       Class to interpret not terminal as bit operation NOT.
|       ├── NonTerminalOr.class
|       |       Class to interpret not terminal as bit operation OR.
|       ├── NonTerminalShiftLeft.class
|       |       Class to interpret not terminal as bit operation SHIFT_LEFT.
|       ├── NonTerminalShiftRight.class
|       |       Class to interpret not terminal as bit operation SHIFT_RIGHT.
|       ├── NonTerminalShiftRightZero.class
|       |       Class to interpret not terminal as bit operation SHIFT_RIGHT_FILL_ZERO.
|       ├── NonTerminalXor.class
|       |       Class to interpret not terminal as bit operation XOR.
|       ├── TerminalNumber.class
|       |       Class to interpret terminal as number.
|       └── UnaryNonTerminal.class
|               Abstract class of not terminal operator with one operand.
├── parser/
|       ├── LexemeParser.class
|       |       Class to parse string into lexemes.
|       ├── ParagraphParser.class
|       |       Class to parse string into sentences.
|       ├── Parser.class
|       |       Abstract parser to realize common behavior.
|       ├── PolishParser.class
|       |       Class to parse infix expression to postfix (polish) expression.
|       ├── SentenceParser.class
|       |       Class to parse string into lexemes with whitespace.
|       ├── TextParser.class
|       |       Class to parse string into paragraphs.
|       └── WordParser.class
|               Class to parse string into symbols.
├── reader/
|       └── DataReader.class
|               Class for reading text files.
└── validator/
        └── FileValidator.class
                Class for validation on the possibility of reading file.
```

### Remarks:
>1. In unit testing in order to create actual and expected objects data takes from the files.
