package lotto

class Lotto {
    val numbers: List<Int> = (1..45).shuffled().take(6)
}
