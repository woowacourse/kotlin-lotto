package lotto.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinLottoTest {
    @Test
    fun `보너스 번호와 당첨번호가 중복되면 예외가 발생한다`() {
        // given
        val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = LottoNumber.from(1)

        // when
        var thrown = assertThrows<IllegalArgumentException> { WinLotto(winNumber, bonus) }
        var except = String.format(
            WinLotto.ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER,
            winNumber.toString(),
            bonus.value
        )

        // then
        Assertions.assertThat(thrown.message).isEqualTo(except)
    }
}
