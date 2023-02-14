import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`(){
        val lotto = Lotto(listOf(1,2,3,4,5,6))
        assertThat(lotto.numbers).isEqualTo(listOf(1,2,3,4,5,6))
    }

    @Test
    fun `로또 당첨번호가 null 일 때`(){
        assertThrows<IllegalArgumentException> { Lotto(null) }
    }

}