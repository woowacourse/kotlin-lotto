package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class LottoNumber1(
    val number: Int,
)

class LottoNumber2(
    val number: Int,
)

@JvmInline
value class LottoNumber3(
    val number: Int,
)

class ClassTest {
    @Test
    fun `데이터 클래스의 동등성은 어떻게 구분하는 지 테스트`() {
        val number = LottoNumber1(1)
        val number2 = LottoNumber1(1)
        assertThat(number).isEqualTo(number2)
        assertThat(number.hashCode()).isEqualTo(number2.hashCode())
    }

    @Test
    fun `클래스의 동등성은 어떻게 구분하는 지 테스트`() {
        val number = LottoNumber2(1)
        val number2 = LottoNumber2(1)
        assertThat(number).isNotEqualTo(number2)
        assertThat(number.hashCode()).isNotEqualTo(number2.hashCode())
    }

    @Test
    fun `value 클래스의 동등성은 어떻게 구분하는 지 테스트`() {
        val number = LottoNumber3(1)
        val number2 = LottoNumber3(1)
        assertThat(number).isEqualTo(number2)
        assertThat(number.hashCode()).isEqualTo(number2.hashCode())
    }

    @Test
    fun `value class hashCode 값 테스트`() {
        val number = LottoNumber3(1)
        val number2 = LottoNumber3(1)
        val number3 = LottoNumber3(1)
        val number4 = LottoNumber3(1)
        val number5 = LottoNumber3(1)
        println(number.hashCode())
        println(number2.hashCode())
        println(number3.hashCode())
        println(number4.hashCode())
        println(number5.hashCode())
    }
}
