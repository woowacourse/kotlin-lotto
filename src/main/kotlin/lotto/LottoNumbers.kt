package lotto

class LottoNumbers {
    val numbers = mutableListOf<Int>()

    init {
        generateNumbers()
    }

    private fun generateNumbers() {
        while (true) {
            val pickedNumber = (1..45).map { it }.shuffled()[0]
            if (pickedNumber !in numbers) {
                numbers.add(pickedNumber)
            }
            if (numbers.size == 6) {
                break
            }
        }
    }

//    fun getNumbers(): MutableList<Int> {
//        return numbers.sorted()
//    }
}
