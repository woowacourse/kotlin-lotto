import kotlin.io.path.fileVisitor

class Lotto(val numbers: List<Int>?) {

    init {
        require( numbers != null) { INPUT_LOTTO_NULL_ERROR_MESSAGE }
        require( numbers.size == 6 ) { INPUT_LOTTO_SIZE_ERROR_MESSAGE }
        require( numbers.toSet().size == 6 ) { INPUT_LOTTO_DUPLICATE_ERROR_MESSAGE }
        require( numbers.filter { number -> number in 1..45 }.size == 6 ) { INPUT_LOTTO_RANGE_ERROR_MESSAGE }
    }

    companion object {
        const val INPUT_LOTTO_NULL_ERROR_MESSAGE = "당첨 번호가 입력되지 않았습니다."
        const val INPUT_LOTTO_SIZE_ERROR_MESSAGE = "당첨 번호가 6개가 아닙니다"
        const val INPUT_LOTTO_DUPLICATE_ERROR_MESSAGE = "당첨 번호가 중복되었습니다."
        const val INPUT_LOTTO_RANGE_ERROR_MESSAGE = "당첨 번호의 범위는 1에서 45 사이의 숫자여야 합니다."
    }
}