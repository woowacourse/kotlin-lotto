package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {

    private val randomLottoGenerator = RandomLottoGenerator()

    @Test
    fun `generate는 로또번호 6개를 생성해준다`() {
        // given
        val expected = 6

        // when
        val actual: Lotto = randomLottoGenerator.generate()

        // then
        assertThat(actual.lottoNumbers.size).isEqualTo(expected)
    }
}
