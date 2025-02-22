package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount
import lotto.model.LottoStatistics
import lotto.model.Lottos
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class LottoStatisticsTest {
    private lateinit var lottos: Lottos
    private lateinit var purchaseAmount: LottoPurchaseAmount

    @BeforeEach
    fun setUp() {
        lottos =
            Lottos(
                listOf(
                    Lotto(1, 2, 3, 4, 5, 6),
                    Lotto(1, 2, 3, 4, 5, 7),
                    Lotto(1, 2, 3, 4, 5, 8),
                ),
            )

        purchaseAmount = LottoPurchaseAmount(3000)
    }

    @ParameterizedTest
    @MethodSource("getWinningLottos")
    fun `당첨 로또 금액에 대한 수익률을 게산한다`(
        winningLotto: WinningLotto,
        rateOfReturn: Double,
    ) {
        val lottoStatistics =
            LottoStatistics(
                lottos = lottos,
                winningLotto = winningLotto,
                purchaseMoney = purchaseAmount,
            )
        val actual = lottoStatistics.getRateOfReturn()
        assertThat(actual).isEqualTo(rateOfReturn)
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 0.9999999])
    fun `수익률이 1 미만 일 경우 수익률 손해를 파악하는 메서드는 true를 반환한다`(rateOfReturn: Double) {
        val lottoStatistics =
            LottoStatistics(
                lottos = lottos,
                winningLotto =
                    WinningLotto(
                        Lotto(1, 2, 3, 4, 5, 6),
                        LottoNumber(7),
                    ),
                purchaseMoney = purchaseAmount,
            )
        val expected = true

        assertThat(lottoStatistics.getIsLossMoney(rateOfReturn)).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(doubles = [1.0, 100.0])
    fun `수익률이 1 이상 일 경우 수익률 손해를 파악하는 메서드는 false 반환한다`(rateOfReturn: Double) {
        val lottoStatistics =
            LottoStatistics(
                lottos = lottos,
                winningLotto =
                    WinningLotto(
                        Lotto(1, 2, 3, 4, 5, 6),
                        LottoNumber(7),
                    ),
                purchaseMoney = purchaseAmount,
            )
        val expected = false

        assertThat(lottoStatistics.getIsLossMoney(rateOfReturn)).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun getWinningLottos(): Stream<Arguments> =
            Stream.of(
                Arguments.arguments(
                    WinningLotto(
                        Lotto(1, 2, 3, 4, 5, 6),
                        LottoNumber(7),
                    ),
                    677166.6666666666,
                ),
                Arguments.arguments(
                    WinningLotto(
                        Lotto(10, 11, 12, 14, 15, 18),
                        LottoNumber(7),
                    ),
                    0.0,
                ),
                Arguments.arguments(
                    WinningLotto(
                        Lotto(1, 2, 3, 4, 10, 11),
                        LottoNumber(13),
                    ),
                    50.0,
                ),
            )
    }
}
