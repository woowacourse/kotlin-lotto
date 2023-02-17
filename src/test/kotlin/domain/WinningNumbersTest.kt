package domain

import domain.lotto.Lotto
import domain.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import util.PREFIX

class WinningNumbersTest {
    @Test
    fun `당첨 번호와 보너스 번호가 중복 되면 에러 발생`() {
        // given
        val lotto = Lotto(
            setOf(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6),
            ),
        )

        val bonusNumber = LottoNumber.of(6)

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { WinningNumbers(lotto, bonusNumber) }
            .withMessageContaining("$PREFIX 보너스 번호가 당첨 번호와 중복되면 안된다.")
    }
}
