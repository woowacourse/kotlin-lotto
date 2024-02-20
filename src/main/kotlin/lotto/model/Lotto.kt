package lotto.model

class Lotto (private val numbers: Set<Int>){

    init{
        require(numbers.size==6)
    }
    fun getNumbers(): Set<Int>{
        return numbers
    }
}