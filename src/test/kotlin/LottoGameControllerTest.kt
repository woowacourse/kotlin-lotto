import domain.Lotto
import domain.LottoCount
import domain.LottoGeneratorInterface
import domain.LottoMachine
import domain.LottoNumber
import domain.Rank
import domain.RankStatistics
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import view.InputViewInterface
import view.OutputViewInterface

class LottoGameControllerTest {

    @Test
    @DisplayName(
        "구매금액은 5000," +
            "수동 로또 구매 수는 1" +
            "수동 로또 번호는 1,2,3,4,5,28" +
            "당첨 로또 번호는 1,2,3,4,6,20" +
            "보너스 번호는 7 일때" +
            "수익률은 10.0 이다"
    )
    fun `Controller`() {
        val expected = 10.0
        val lottoGameController = LottoGameController(
            TestInputView(),
            TestOutPutView { assertThat(it).isEqualTo(expected) },
            LottoMachine(TestLottoGenerator())
        )
        lottoGameController.run()
    }

    class TestLottoGenerator() : LottoGeneratorInterface {
        override fun generateLotto(): Lotto {
            return Lotto(
                listOf(
                    LottoNumber(10),
                    LottoNumber(11),
                    LottoNumber(12),
                    LottoNumber(13),
                    LottoNumber(14),
                    LottoNumber(15),
                )
            )
        }
    }

    class TestInputView() : InputViewInterface {
        override fun inputPaymentMoney(): Int {
            return 5000
        }

        override fun inputManualLottoCount(): Int {
            return 1
        }

        override fun inputManualLottoNumbers(): List<Int> {
            return listOf(1, 2, 3, 4, 5, 28)
        }

        override fun inputWinningLottoNumbers(): List<Int> {
            return listOf(1, 2, 3, 4, 6, 20)
        }

        override fun inputBonusNumber(): Int {
            return 7
        }
    }

    class TestOutPutView(val assertProfitRate: (Double) -> Unit) : OutputViewInterface {
        override fun printLottoCountResult(lottoCount: LottoCount) {
        }

        override fun printAutomaticLottoNumbers(lotto: Lotto) {
        }

        override fun printAutomaticLotteries(lotteries: List<Lotto>) {
        }

        override fun printRankStatistics(rankStatistics: RankStatistics) {
        }

        override fun printRankCount(rank: Rank, count: Int) {
        }

        override fun printProfitRate(profitRate: Double, isLoss: Boolean) {
            assertProfitRate(profitRate)
        }

        override fun printErrorMessage(errorMessage: String) {
        }
    }
}
