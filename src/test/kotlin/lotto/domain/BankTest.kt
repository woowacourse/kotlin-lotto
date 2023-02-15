package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankTest {
    private val bank = Bank()

    @Test
    fun `로또 번호와 당첨번호가 6개 같다(6을 반환)`() {
        assertThat(
            bank.countMatchedMainLottoNumber(
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
            bank.checkMatchedBonusLottoNumber(
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

    @Test
    fun `로또 3개 중 2등과 3등이 하나씩 당첨되었다면 당첨 금액의 합계는 삼천백오십만원 이다`() {
        bank.sumTotalPrizeMoney(
            LottoBunch(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(7),
                    ),
                ),
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(8),
                    ),
                ),
                Lotto(
                    listOf(
                        LottoNumber(11),
                        LottoNumber(12),
                        LottoNumber(13),
                        LottoNumber(14),
                        LottoNumber(17),
                        LottoNumber(16),
                    ),
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
        )
    }

    @Test
    fun `14000원으로 5000원을 번다면 수익률은 35%이다`() {
        assertThat(bank.getYieldRate(PurchaseMoney(14000), 5000)).isEqualTo(0.35714285714285715)
    }
}
