package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @ParameterizedTest
    @MethodSource("당첨 번호는 총 6개를 입력해야 한다 테스트 케이스")
    fun `당첨 번호는 총 6개를 입력해야 한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> { Lotto(numbers.map(::LottoNumber)) }
    }

    @Test
    fun `로또 번호는 중복될 수 없다`() {
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 1, 2, 3) }
    }

    companion object {
        @JvmStatic
        fun `당첨 번호는 총 6개를 입력해야 한다 테스트 케이스`() =
            listOf(
                listOf(),
                listOf(1),
                listOf(1, 2),
                listOf(1, 2, 3),
                listOf(1, 2, 3, 4),
                listOf(1, 2, 3, 4, 5),
                listOf(1, 2, 3, 4, 5, 6, 7),
            )
    }
}
