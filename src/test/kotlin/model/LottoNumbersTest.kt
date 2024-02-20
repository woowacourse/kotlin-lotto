package model

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows

class LottoNumbersTest {
    @Test
    fun `구매한 로또 번호 6개를 저장한다`() {
        assertThrows<IllegalArgumentException>{
            LottoNumbers(listOf(1,2,3,4,5,6,7))
        }
    }
    @Test
    fun `로또 번호는 1 ~ 45 이어야 한다`() {
        assertThrows<IllegalArgumentException>{
            LottoNumbers(listOf(0,99,377,422,511,642))
        }
    }
}