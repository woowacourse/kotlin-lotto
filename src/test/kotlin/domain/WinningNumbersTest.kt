package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class WinningNumbersTest {
    @Test
    fun `당첨 번호와 보너스 번호가 중복 되면 에러 발생`() {
        // given
        val lotto = Lotto(
            setOf(
                LottoNumber.create(1),
                LottoNumber.create(2),
                LottoNumber.create(3),
                LottoNumber.create(4),
                LottoNumber.create(5),
                LottoNumber.create(6),
            )
        )

        val bonusNumber = LottoNumber.create(6)

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { WinningNumbers(lotto, bonusNumber) }
            .withMessageContaining("${WinningNumbers.PREFIX} 보너스 번호가 당첨 번호와 중복되면 안된다.")
    }
}
