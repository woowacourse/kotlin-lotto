package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ManualCountTest {

    @ValueSource(ints = [0, 1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `최대 구매할 수 있는 로또 개수 내에서 값을 받을 수 있다`(count: Int) {
        // given
        val maxCount = 5

        // when

        // then
        assertThat(ManualCount(count, maxCount).count).isEqualTo(count)
    }

    @ValueSource(ints = [-2, -1, 6, 7])
    @ParameterizedTest
    fun `음수값이 들어오거나 최대로 구매할 수 있는 로또 개수를 초과할 수 없다`(count: Int) {
        // given
        val maxCount = 5

        // when
        val exception = assertThrows<IllegalArgumentException> { ManualCount(count, maxCount) }

        // then
        assertThat(exception.message).isEqualTo("[Error] 0부터 최대 5 까지 입력하실 수 있습니다.")
    }

    @ValueSource(strings = ["", " ", "a"])
    @ParameterizedTest
    fun `정수형으로 된 값만 받을 수 있다`(count: String) {
        // given
        val maxCount = 5

        // when
        val exception = assertThrows<NumberFormatException> { ManualCount(count, maxCount) }

        // then
        assertThat(exception.message).isEqualTo("[Error] 숫자로 입력해주세요.")
    }

    @Test
    fun `자동으로 발급해야할 로또 개수를 계산한다`() {
        // given
        val manualCount = ManualCount(count = 3, maxCount = 5)

        // when
        val expected: Int = manualCount.calculateAutoLottoCount()

        // then
        assertThat(expected).isEqualTo(2)
    }
}
