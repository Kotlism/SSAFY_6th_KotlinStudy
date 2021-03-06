#### πλ³μ νμ
  + var : μμ  κ°λ₯ν λ³μ
  + val : μμ  λΆκ°λ₯ν λ³μ

kotlinμ λ³μμ νμμ μ΄ν΄ν  μ λλ‘ μΆ©λΆν λλνλ κ°λ°μκ° μλμ μΌλ‘ νμμ κ΅¬μ²΄ννλ κ²λ κ°λ₯.
```kotlin
var name = "John"      // String (text)
val birthyear = 1975   // Int (number)

println(name)          // Print the value of name
println(birthyear)     // Print the value of birthyear
```
```kotlin
var name: String = "John" // String
val birthyear: Int = 1975 // Int

println(name)
println(birthyear)
```
νμμ λͺμν  κ²½μ°μλ§ μ΄κΈ°ν μμ΄ μ μΈ κ°λ₯
```kotlin
var name: String
name = "John"
println(name)		// (o)
```
```kotlin
var name
name = "John"
println(name)		// (x)
```

#### πkotlinλ³μμ μΌλ°μ μΈ κ·μΉλ€μ
1. λ³μλͺλ€μ λ¬Έμ, μ«μ, \_, $λ₯Ό ν¬ν¨ν  μ μλ€.
2. λ³μλͺλ€μ λ¬Έμλ‘ μμν΄μΌνλ€.
3. λ³μλͺλ€μ λν $μ \_λ‘ μμν  μ μλ€.(κ·Έλ¬λ μ΄κ²λ€λ§ μΈ μλ μλ€.)
4. λ³μλͺλ€μ λμλ¬Έμλ₯Ό κ΅¬λΆνλ€. (myVar != myvar)
5. λ³μλͺλ€μ μλ¬Έμλ‘ μμνλ κ²μ΄ μ’μΌλ©°, κ³΅λ°±μ ν¬ν¨ν  μ μλ€.
6. μμ½μ΄(var, String λ±)λ λ³μλͺμΌλ‘ μ¬μ©ν  μ μλ€.

#### πλ°μ΄ν° νμ
  - Numbers
    + Integer types : Byte, Short, Int, Long
    + Floating point types : Float, Double
  - Booleans
    + Boolean (true, false)
  - Characters
    + Char
      >(** javaμ λ€λ₯΄κ² ASCII κ°μΌλ‘ νΉμ  λ¬Έμλ₯Ό μ¬μ©ν  μ μλ€. **)
      > > ```kotlin
      > >val myLetter: Char = 66
      > >println(myLetter) // Error
      > >```
  - Strings
    + String

#### πνμ λ³ν
 - varλ κ°μ λ³κ²½ν  μ μμ§λ§ λ€λ₯Έ νμ κ°μ λ£μ μ μλ€. κ΅³μ΄ νμμ λ°κΎΈκ³  μΆλ€λ©΄, κ°μ  νλ³ν(coerce)μ ν΅ν΄ κ°λ₯νλ€.
 - μλ°μ λ€λ₯΄κ² μλνλ³νμ΄ λΆκ°λ₯νλ€.
```kotlin
val x: Int = 5
val y: Long = x
println(y) // Error: Type mismatch
```
 - λ°λμ λ€μμ ν¨μλ€μ μ¬μ©νμ¬ λ³νν΄μΌνλ€.   
    : toByte(), toShort(), toInt(), toLong(), toFloat(), toDouble() or toChar()
```kotlin
val x: Int = 5
val y: Long = x.toLong()
println(y)
```

#### πKotlinμ λ€μμ κ·Έλ£ΉμΌλ‘ μ°μ°μλ€μ λλλ€.
 - μ°μ μ°μ°μ
 - ν λΉ μ°μ°μ
 - λΉκ΅ μ°μ°μ
 - λΌλ¦¬ μ°μ°μ

#### πString Concatenation
```kotlin
var firstName = "John"
var lastName = "Doe"
println(firstName + " " + lastName)
```
```kotlin
var firstName = "John "
var lastName = "Doe"
println(firstName.plus(lastName))
```

#### πString Templates/Interpolation
```kotlin
var firstName = "John"
var lastName = "Doe"
println("My name is $firstName $lastName")
```

#### πKothlin if .. else expressions
if .. else λͺλ Ήλ¬Έμ ννμμΌλ‘ μ¬μ© κ°λ₯νλ€.
```kotlin
fun main() {
  val time = 20
  val greeting = if (time < 18) {
    "Good day."
  } else {
    "Good evening."
  }
  println(greeting)
}
```
λͺλ Ήλ¬Έμ΄ νμ€μ΄λ©΄ {}μλ΅κ°λ₯
```kotlin
fun main() {
  val time = 20
  val greeting = if (time < 18) "Good day." else "Good evening."
  println(greeting)
}
```

#### πKothlin when
μλ°μ switchλ¬Έκ³Ό μ μ¬νλ€. λ¨, caseλ§λ€ breakν  νμ μλ€.
```kotlin
val day = 4

val result = when (day) {
  1 -> "Monday"
  2 -> "Tuesday"
  3 -> "Wednesday"
  4 -> "Thursday"
  5 -> "Friday"
  6 -> "Saturday"
  7 -> "Sunday"
  else -> "Invalid day."
}
println(result)
```

#### πkotlin ν¨μ
```kotlin
fun myFunction(fname: String, age: Int) {
  println(fname + " is " + age)
}

fun main() {
  myFunction("John", 35)
  myFunction("Jane", 32)
  myFunction("George", 15)
}

// John is 35
// Jane is 32
// George is 15
```

#### πreturn values
```kotlin
fun myFunction(x: Int, y: Int): Int {
  return (x + y)
}

fun main() {
  var result = myFunction(3, 5)
  println(result)
}

// 8 (3 + 5)
```
```kotlin
fun myFunction(x: Int, y: Int) = x + y

fun main() {
  var result = myFunction(3, 5)
  println(result)
}

// 8 (3 + 5)
```

   *   *   *
   
 #### πKotlin Arrays
 1. λΌμ΄λΈλ¬λ¦¬ ν¨μλ₯Ό μ¬μ©νλ λ°©λ²
   >|ν¨μ|μ€λͺ|
   >|:---:|:----|
   >|arrayOf(value...)|μ μΈκ³Ό λμμ κ°λ€μ μ΄κΈ°ν|
   >|arrayOfNulls(size)|ν¬κΈ°λ§ μ§μ νκ³  κ°μ λμ€μ μ μ₯, μ§μ λ ν¬κΈ°λ§νΌ nullκ° μΈν|
   > ```kotlin
   > val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")    // ["Volvo", "BMW", "Ford", "Mazda"]
   > val nums = arrayOfNulls<Int>(3)      // [null, null, null]
   > ```
 2. μμ±μλ₯Ό μ¬μ©νλ λ°©λ²
   > |μμ±μ|μμ|μ€λͺ|
   > |:----:|:--:|:---|
   > |Array(size, λλ€μ)|Array(5, {i -> i}|[0, 1, 2, 3, 4]λ‘ μ΄κΈ°ν|
   > |xxxArray(size)|IntArray(3)|[0, 0, 0] λ‘ μ΄κΈ°ν λ Int ν λ°°μ΄|
   > ```kotlin
   > val array: Array<Int> = Array(5, {i -> 0})    // [0, 0, 0, 0]
   > val array2: Array<Int> = Array(5) { i -> sc.nextInt() }   // Scanner μ¬μ©, μλ ₯λ°μ μμλλ‘ λ°°μ΄μ μ΄κΈ°ν
   > val array3 = IntArray(5)    // [0, 0, 0, 0, 0]
   > ```
 3. Boxingμ λ°μμν€μ§ μλ λ°°μ΄ μμ±
    Primitive typeμ λ°°μ΄μ μμ±ν  λ, μμ κ°μ΄ Array ν΄λμ€λ‘ μμ±νκ² λλ©΄ WrappingμΌλ‘ μΈν Boxing λ°μ
    μ½νλ¦°μμλ Primitive typeμ νν΄μ κ°κ°μ νΉμ ν΄λμ€λ₯Ό μ κ³΅
   > |ν΄λμ€|
   > |:----:|
   > |xxxArrayOf(value...)|
   > ```kotlin
   > val array: IntArray = intArrayOf(1, 2, 3)    // [1, 2, 3]
   > val array2: DoubleArray = doubleArrayOf(0.5, 0.2)   // [0.5, 0.2]
   > val array3: BooleanArray = booleanArrayOf(true, false)    // [true, false]
   > ```
 + valμ λ³κ²½μ΄ λΆκ°λ₯ν κ²μ΄ λ§λ€. νμ§λ§, μ°Έμ‘°κ° κ°λ¦¬ν€λ κ°μ²΄μ λ΄λΆκ°μ λ³κ²½μ΄ κ°λ₯νλ€.

#### πμμ μ λ¬΄ μ²΄ν¬
```kotlin
val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
if ("Volvo" in cars) {
  println("It exists!")
} else {
  println("It does not exist.")
}
```

#### πKotlin forλ¬Έ : in
```kotlin
for ( x in cars) {
  println(x)
}
```

#### πKotlin Ranges
```kotlin
for (chars in 'a'..'x') {
  println(chars)     // a, b, c, d, ... , x
}

for (ints in 1..10) {
  println(ints)      // 1, 2, 3, ..., 9, 10
}
   
for (ints in 1..10 step 2) {
   println(ints)     // 1, 3, 5, 7, 9
}
```

κ°μλ downTo
```kotlin
for (chars in 'x' downTo 'a') {
  println(chars)
}

for (ints in 10 downTo 1) {
  println(ints)      // 10, 9, 8, ... , 2, 1
}

for (ints in 10 downTo 1 step 2) {
  println(ints)      // 10, 8, 6, 4, 2
}
```
until
```kotlin
   for (ints in 1 until 10) {
  println(ints)      // 1, 2, ..., 8, 9
}
```

#### π Kotlin forλ¬Έ λ²μμ λ€μν νν
```kotlin
fun forLoop(){
    println("[for] λ°λ³΅λ¬Έ")
    val items = listOf("apple", "banana", "kiwi")
    
    // A
    for(item in items) {
        println(item)
    }
    // B
    for(index in 0..(items.size-1)) {
        println("μ΄κ±΄ item at $index is ${items[index]}")
    }
    // C
    for(index in 0 until items.size) {
        println("μ΄κ±΄ item at $index is ${items[index]}")
    }
    // D
    for(index in items.indices) { //indices -> 0..2
        println("item at $index is ${items[index]}")
    }
}
```
   
#### π Input
 1. javaμ Inputλ€ μ¬μ©
   >```kotlin
   > val sc = Scanner(System.`in`)
   >```
 2. readLine
   >```kotlin
   > val s = readLine()
   > var n = readLine()!!.toInt()      // !! nullκ°μ΄ μλλ€. nullκ°μ λ°μ μ μλ€
   > var tmp = readLine()?.toString()  // ? nullκ°μΌ μλ μλ€.
   >```
   
*   *   *

#### π chunked
```kotlin
s:String.chunked(size:Int)    // return List<String>
```
sizeλ μλ₯Ό ν¬κΈ°μ΄λ€.
ex. sizeκ° 2 μ΄λ©΄, 2λ¬Έμμ© λ¬Έμμ΄μ μλ₯΄κ² λ€λ μλ―Έ
<br />
Pair   

```kotlin
Pair<T, T> = Pair(value: T, value: T)
```