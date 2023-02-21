package lotto.domain

import lotto.constant.BonusResult.BONUS_MATCH
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankTest {

    @Test
    fun `로또 번호와 당첨번호가 6개 같다(6을 반환)`() {
        assertThat(
            Bank.countMatchedMainLottoNumber(
                Lotto(
                    listOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(6),
                    ),
                ),
                WinningLotto(
                    listOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(6),
                    ),
                    LottoNumber.from(7),
                ),
            ),
        ).isEqualTo(6)
    }

    @Test
    fun `보너스 번호가 당첨되었다`() {
        assertThat(
            Bank.checkMatchedBonusLottoNumber(
                Lotto(
                    listOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(7),
                        LottoNumber.from(6),
                    ),
                ),
                WinningLotto(
                    listOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(6),
                    ),
                    LottoNumber.from(7),
                ),
            ),
        ).isEqualTo(BONUS_MATCH)
    }

    @Test
    fun `로또 3개 중 2등과 3등이 하나씩 당첨되었다면 당첨 금액의 합계는 삼천백오십만원 이다`() {
        Bank.sumTotalPrizeMoney(
            LottoBunch(
                listOf(
                    Lotto(
                        listOf(
                            LottoNumber.from(1),
                            LottoNumber.from(2),
                            LottoNumber.from(3),
                            LottoNumber.from(4),
                            LottoNumber.from(5),
                            LottoNumber.from(7),
                        ),
                    ),
                    Lotto(
                        listOf(
                            LottoNumber.from(1),
                            LottoNumber.from(2),
                            LottoNumber.from(3),
                            LottoNumber.from(4),
                            LottoNumber.from(5),
                            LottoNumber.from(8),
                        ),
                    ),
                    Lotto(
                        listOf(
                            LottoNumber.from(11),
                            LottoNumber.from(12),
                            LottoNumber.from(13),
                            LottoNumber.from(14),
                            LottoNumber.from(17),
                            LottoNumber.from(16),
                        ),
                    ),
                ),
            ),
            WinningLotto(
                listOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                ),
                LottoNumber.from(7),
            ),
        )
    }

    @Test
    fun `14000원으로 5000원을 번다면 수익률은 35%이다`() {
        assertThat(Bank.getYieldRate(PurchaseMoney(14000), 5000)).isEqualTo(0.35714285714285715)
    }
}
