package domaintest.modeltest

import domain.model.LottoResult
import domain.model.WinningLotto
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import domain.model.lotto.PurchasedLottos
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchasedLottosTest {

    @Test
    fun `구입한 로또의 전체 당첨 결과를 확인하기`() {
        val purchasedLottos = PurchasedLottos(
            lottos = listOf(
                Lotto(
                    setOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(6)
                    )
                ),
                Lotto(
                    setOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(7)
                    )
                ),
                Lotto(
                    setOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(7),
                        LottoNumber.from(9)
                    )
                )
            )
        )

        assertThat(
            purchasedLottos.getTotalLottoResults(
                winningLotto = WinningLotto(
                    catchLotto = Lotto(
                        setOf(
                            LottoNumber.from(1),
                            LottoNumber.from(2),
                            LottoNumber.from(3),
                            LottoNumber.from(4),
                            LottoNumber.from(5),
                            LottoNumber.from(6)
                        )
                    ),
                    bonusNumber = LottoNumber.from(7)
                )
            )
        ).isEqualTo(
            listOf(
                LottoResult.FIRST,
                LottoResult.SECOND,
                LottoResult.FORTH
            )
        )
    }
}
