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
import java.util.stream.Stream

class LottoStatisticsTest {
    private lateinit var lottos: Lottos
    private lateinit var purchaseAmount: LottoPurchaseAmount

    @BeforeEach
    fun setUp() {
        lottos =
            Lottos(
                listOf(
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

    companion object {
        @JvmStatic
        fun getWinningLottos(): Stream<Arguments> =
            Stream.of(
                Arguments.arguments(
                    WinningLotto(
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
                        LottoNumber(7),
                    ),
                    677166.6666666666,
                ),
                Arguments.arguments(
                    WinningLotto(
                        Lotto(
                            listOf(
                                LottoNumber(10),
                                LottoNumber(11),
                                LottoNumber(12),
                                LottoNumber(14),
                                LottoNumber(15),
                                LottoNumber(18),
                            ),
                        ),
                        LottoNumber(7),
                    ),
                    0.0,
                ),
                Arguments.arguments(
                    WinningLotto(
                        Lotto(
                            listOf(
                                LottoNumber(1),
                                LottoNumber(2),
                                LottoNumber(3),
                                LottoNumber(4),
                                LottoNumber(10),
                                LottoNumber(11),
                            ),
                        ),
                        LottoNumber(13),
                    ),
                    50.0,
                ),
            )
    }
}
