package lotto.domain

import lotto.model.Lotto
import lotto.model.generator.LottosGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.round

class LottoControllerTest {

    @Test
    fun `컨트롤러로부터 수익률이 잘 계산되는지 확인한다`() {
        val actual: MutableList<String> = mutableListOf()
        val controller = LottoController(
            FakeInputView(),
            FakeOutputView { actual.add(it) },
            TestGenerator()
        )

        controller.start()

        assertThat(actual).containsExactlyInAnyOrder(
            "수동 1장 자동 3장",
            "수익률 ${round((5_000 + 30_000_000 + 50_000) / 4000.toDouble() * 100) / 100}"
        )
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
