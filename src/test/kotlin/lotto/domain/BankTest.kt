package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankTest {
    @Test
    fun `로또 번호와 당첨번호가 6개 같다(6을 반환)`() {
        assertThat(
            Bank().countMatchedMainLottoNumber(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
                WinningLotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                    LottoNumber(7),
                ),
            ),
        ).isEqualTo(6)
    }

    @Test
    fun `보너스 번호가 당첨되었다`() {
        assertThat(
            Bank().checkMatchedBonusLottoNumber(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(7),
                        LottoNumber(6),
                    ),
                ),
                WinningLotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                    LottoNumber(7),
                ),
            ),
        ).isTrue
    }
}
