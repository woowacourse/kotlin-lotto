package lottoTest.domain

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoTest {
    @Test
    @DisplayName("로또는 1부터 45까지의 번호만을 입력 받을 수 있다")
    fun t1() {
        assertDoesNotThrow { Lotto(listOf(1, 2, 3, 4, 5, 45).map { LottoNumber.of(it) }) }
    }

    @Test
    @DisplayName("로또는 중복된 숫자를 입력 받을 수 없다")
    fun t2() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5, 5).map { LottoNumber.of(it) }) }
    }

    @Test
    @DisplayName("로또는 6개의 숫자만을 입력 받을 수 있다")
    fun t3() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5).map { LottoNumber.of(it) }) }
    }

    @Test
    @DisplayName("  - 6개의 숫자를 입력받지 않는다면 IllegalArgumentException를 반환하고 \"6개의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t4() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 6, 7).map { LottoNumber.of(it) }) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("6개의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 하나의 숫자 이상이 1부터 45까지의 범위가 아니라면 IllegalArgumentException를 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t5() {
        assertThatThrownBy { Lotto(listOf(1, 2, 300, 4, 5, 6).map { LottoNumber.of(it) }) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 중복된 숫자를 입력한 경우 IllegalArgumentException을 반환하고 \"중복되지 않은 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t6() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 5).map { LottoNumber.of(it) }) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("중복되지 않은 숫자를 입력해주세요")
    }
}
