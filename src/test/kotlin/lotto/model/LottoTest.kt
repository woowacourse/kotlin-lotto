package lotto.model

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 1매에는 6개의 로또 번호가 존재한다`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto.from(listOf(1, 2, 3, 4, 5)) }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 번호는 6개여야 합니다.")
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto.from(listOf(1, 1, 2, 3, 4, 5)) }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 번호는 중복될 수 없습니다.")
    }

    @Test
    fun `두 로또 사이에서 일치하는 숫자의 개수를 반환한다`() {
        val lotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto.from(listOf(1, 2, 3, 4, 5, 7))

        lotto.countMatchingNumber(winningLotto) shouldBe 5
    }

    @Test
    fun `특정 번호의 포함 여부를 반환한다`() {
        val lotto = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber(1)

        lotto.isContain(bonusNumber) shouldBe true
    }
}
