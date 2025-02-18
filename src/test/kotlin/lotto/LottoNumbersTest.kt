package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoNumbersTest {
    @Test
    fun `1에서 45까지 숫자를 한개 뽑는다`() {
        val number = (1..45).random()
    }

    @Test
    fun `리스트 원소가 6개가 될때까지, 중복 없이 숫자를 추가한다`() {
        val lottoNumbers = mutableListOf<Int>()
        val numbers = mutableListOf(1, 2, 3, 4, 5, 5, 6, 7)
        for (number in numbers) {
            if (number !in lottoNumbers) {
                lottoNumbers.add(number)
            }
            if (lottoNumbers.size == 6) break
        }
        assertThat(lottoNumbers.size).isEqualTo(6)
    }
}
