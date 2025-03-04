package lottoTest.domain

import lotto.domain.LottoNumber
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoNumberTest {
    @Test
    @DisplayName("  - 1부터 45 사이의 숫자를 입력받지 않는다면 IllegalArgumentException을 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t3_2() {
        assertThatThrownBy { LottoNumber.of(1234) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }

    @Test
    @DisplayName("  - 1부터 45 사이의 숫자를 입력받지 않는다면 IllegalArgumentException을 반환하고 \"1부터 45까지의 숫자를 입력해주세요\" 라는 메시지를 출력한다\n")
    fun t3_2_1() {
        assertThatThrownBy { LottoNumber.of(-1) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1부터 45까지의 숫자를 입력해주세요")
    }
}
