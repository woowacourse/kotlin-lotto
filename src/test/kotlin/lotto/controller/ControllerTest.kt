package lotto.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ControllerTest {

    @Test
    fun `컨트롤러를 테스트한다`() {
        val actual = mutableListOf<Any>()
        val controller = Controller(TestInputView(), TestOutputView(actual), TestLottoNumberGenerator())
        controller.start()
        val expected = listOf(
            "수동으로 2장, 자동으로 2장 구매",
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(1, 4, 9, 19, 24, 36),
                listOf(27, 29, 30, 31, 33, 40)
            ),
            listOf("FIFTH", "FIFTH", "MISS", "MISS", "수익률은 2.50")
        )
        println(actual)
        println(expected)
        assertThat(actual).isEqualTo(expected)
    }
}
