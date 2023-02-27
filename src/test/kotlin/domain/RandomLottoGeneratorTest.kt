package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {

    @Test
    fun `임의의 로또번호 6개를 생성한다`() {
        // given
        val randomLottoGenerator = RandomLottoGenerator()

        // when
        randomLottoGenerator.autoGenerate(1)

        // then
        assertThat(randomLottoGenerator.autoLottos[0].lottoNumbers.size).isEqualTo(6)
    }

    @Test
    fun `중복이 없는 로또 번호 6개를 생성한다`() {
        // given
        val randomLottoGenerator = RandomLottoGenerator()

        // when
        randomLottoGenerator.autoGenerate(1)
        val actual: List<LottoNumber> = randomLottoGenerator.autoLottos[0].lottoNumbers

        // then
        assertThat(actual.distinct().size).isEqualTo(6)
    }
}
