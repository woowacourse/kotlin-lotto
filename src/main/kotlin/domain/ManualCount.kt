package domain

class ManualCount(val count: Int, private val maxCount: Int) {

    init {
        require(count in 0..maxCount) { "[Error] 0부터 최대 $maxCount 까지 입력하실 수 있습니다." }
    }

    constructor(count: String, maxCount: Int) : this(
        count.toIntOrNull() ?: throw NumberFormatException("[Error] 숫자로 입력해주세요."), maxCount
    )
}
