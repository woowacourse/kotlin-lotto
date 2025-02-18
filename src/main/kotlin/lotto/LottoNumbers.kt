package lotto

class LottoNumbers {
    val numbers = mutableListOf<Int>()

    init {
        generateNumbers()
    }

    fun generateNumber(): Int = (1..45).random()

    fun generateNumbers(): List<Int> {
        while (true) {
            val number = generateNumber()
            if (number !in numbers) {
                numbers.add(number)
            }
            if (numbers.size == 6) break
        }
        return numbers
    }
}
