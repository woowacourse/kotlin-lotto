package lotto.domain

import lotto.model.Lotto
import lotto.model.generator.LottosGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoControllerTest {

    @Test
    fun `컨트롤러로부터 수익률이 잘 계산되는지 확인한다`() {
        val actual: MutableList<String> = mutableListOf()
        val controller = LottoController(
            FakeInputView(
                purchaseMoney = 2000,
                manualLottoCount = 1,
                manualLottoNumbers = listOf(1, 2, 3, 4, 5, 8),
                winningLottoNumbers = listOf(1, 2, 3, 4, 5, 6),
                winningBonusNumber = 7,
            ),
            FakeOutputView { actual.add(it) },
            TestGenerator()
        )

        controller.start()

        assertThat(actual).containsExactlyInAnyOrder(
            "수동 1장 자동 1장",
            "1, 2, 3, 4, 5, 8",
            "2, 4, 6, 8, 10, 12",
            "수익률 752.5"
        )
    }

    inner class TestGenerator : LottosGenerator {
        override fun generate(count: Int): List<Lotto> {
            return listOf(Lotto.create(listOf(2, 4, 6, 8, 10, 12)))
        }
    }
}
