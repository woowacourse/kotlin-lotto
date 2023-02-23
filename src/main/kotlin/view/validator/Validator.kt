package view.validator

object Validator {
    fun validateConvertInt(input: String): Int? {
        val number = input.toIntOrNull()
        if (number == null)
            println(ERROR_CONVERT_INT)
        return number
    }

    fun validateConvertToIntList(input: String, delimiter: String): List<Int>? {
        val numbers = input.split(delimiter).map { it.trim().toIntOrNull() }
        if (numbers.contains(null)) {
            println(ERROR_CONVERT_INT_LIST.format(delimiter))
            return null
        }
        return numbers.map { it!! }
    }

    private const val ERROR_CONVERT_INT = "[ERROR] 숫자로 다시 입력해주세요."
    private const val ERROR_CONVERT_INT_LIST = "[ERROR] %s로 숫자를 구분해서 다시 입력해주세요."
}
