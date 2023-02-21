package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {

    private val randomLottoGenerator = RandomLottoGenerator()

    @Test
    fun `임의의 로또번호 6개를 생성한다`() {
        // given

        // when
        val actual: Lotto = randomLottoGenerator.generate()

        // then
        assertThat(actual.lottoNumbers.size).isEqualTo(6)
    }

    @Test
    fun `중복이 없는 로또 번호 6개를 생성한다`() {
        // given

        // when
        val actual = randomLottoGenerator.generate().lottoNumbers.distinct().size

        // then
        assertThat(actual).isEqualTo(6)

    }
}
