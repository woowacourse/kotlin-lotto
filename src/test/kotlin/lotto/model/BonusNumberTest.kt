package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 45])
    fun `번호를 하나 가진다`(number: Int) {
        val bonusNumber = BonusNumber(number)
        assertThat(bonusNumber.number).isEqualTo(number)
    }
}
