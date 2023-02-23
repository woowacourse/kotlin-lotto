package lotto.domain

import lotto.model.Lotto
import lotto.model.UserLotto
import lotto.model.UserLottoCount
import lotto.model.generator.LottosGenerator
import lotto.view.InputInterface
import lotto.view.OutputInterface
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.round

class LottoControllerTest {

    @Test
    fun `컨트롤러로부터 수익률이 잘 계산되는지 확인한다`() {
        val actual: MutableList<String> = mutableListOf()
        val controller = LottoController(
            TestInputView(),
            TestOutputView { actual.add(it) },
            TestGenerator()
        )

        controller.start()

        assertThat(actual).containsExactlyInAnyOrder(
            "수동 1장 자동 3장",
            "수익률 ${round((5_000 + 30_000_000 + 50_000) / 4000.toDouble() * 100) / 100}"
        )
    }

    inner class TestInputView() : InputInterface {

        override fun getPurchaseMoney(): Int {
            return 4000
        }

        override fun getManualLottoCount(): Int {
            return 1
        }

        override fun getManualLottoNumbers(): List<Int> {
            return listOf(4, 5, 6, 7, 8, 9)
        }

        override fun getWinningLottoNumbers(): List<Int> {
            return listOf(1, 2, 3, 4, 5, 6)
        }

        override fun getWinningBonusNumber(): Int {
            return 7
        }
    }

    inner class TestOutputView(
        val onPrintResult: (String) -> Unit
    ) : OutputInterface {
        override fun printPurchaseCounts(userLottoCount: UserLottoCount) {
            onPrintResult("수동 1장 자동 3장")
        }

        override fun printUserLottos(userLotto: UserLotto) {
        }

        override fun printResult(ranks: List<Int>, rates: String) {
            onPrintResult("수익률 $rates")
        }
    }

    inner class TestGenerator : LottosGenerator {
        private val autoLotto = mutableListOf(
            Lotto.create(listOf(1, 2, 3, 4, 5, 7)),
            Lotto.create(listOf(1, 2, 3, 4, 10, 20)),
            Lotto.create(listOf(11, 12, 13, 14, 15, 16))
        )

        override fun generate(count: Int): List<Lotto> {
            return autoLotto
        }
    }
}
