package lotto.domain

class Lotto(randomNumbers: List<Int>) {
    val numbers: List<Int> = randomNumbers.sorted()
}
