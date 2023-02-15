package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class LottoTest {
    @Test
    fun `숫자가 6개인 로또 번호를 뽑는다`() {
        assertDoesNotThrow {
            Lotto.create(listOf(1, 24, 34, 35, 36, 38).toSet())
        }
    }

    @Test
    fun `숫자가 6개가 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.create(listOf(1, 24, 34, 35, 37, 45, 22).toSet())
        }
    }

    @Test
    fun `중복된 숫자 6개를 이용해 로또를 생성하면 에러가 발생한다`() {
        val numbers = listOf(1, 1, 2, 3, 4, 5).toSet()

        assertThatIllegalArgumentException()
            .isThrownBy { Lotto.create(numbers) }
            .withMessage("로또 번호의 개수는 6개여야 합니다. \n 잘못된 값 : ${numbers.size}")
    }
}
