package lotto.domain.model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가지지 않으면 예외가 발생한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
        assertThatThrownBy { Lotto(numbers) }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("$numbers 중복을 제외한 로또 번호 입니다. 로또 번호는 6개여야 합니다.")
    }

    @Test
    fun `중복된 번호를 제거한 뒤 번호가 6개가 아니라면 예외가 발생한다`() {
        val numbers = listOf(1, 1, 2, 3, 4, 5)
        assertThatThrownBy { Lotto(numbers) }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${numbers.toSet()} 중복을 제외한 로또 번호 입니다. 로또 번호는 6개여야 합니다.")
    }
}
