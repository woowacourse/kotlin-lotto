package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @ParameterizedTest
    @MethodSource("provideInvalidNumbers")
    fun `로또 번호들은 중복이 없는 6개여야한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(LottoNumbers(numbers.map { LottoNumber.of(it) }))
        }
    }

    companion object {
        @JvmStatic
        fun provideInvalidNumbers() =
            listOf(
                listOf(1, 2, 3, 3, 4, 5),
                listOf(1, 2, 3, 4, 5),
                listOf(1, 2, 3, 4, 5, 6, 6),
                listOf(1, 2, 3, 4, 5, 6, 7),
            )
    }
}
