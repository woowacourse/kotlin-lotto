package lottoTest.domain

import lotto.domain.Lotto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoTest {
    @Test
    @DisplayName("로또는 1부터 45까지의 번호만을 입력 받을 수 있다")
    fun t1() {
        assertDoesNotThrow { Lotto(listOf(1, 2, 3, 4, 5, 45)) }
    }

    @Test
    @DisplayName("로또는 중복된 숫자를 입력 받을 수 없다")
    fun t2() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5, 5)) }
    }

    @Test
    @DisplayName("로또는 6개의 숫자만을 입력 받을 수 있다")
    fun t3() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5)) }
    }
}
