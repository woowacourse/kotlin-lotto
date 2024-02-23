import model.Lotto
import model.NumberGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PublishLottoTest {
    private lateinit var lottoNumbers: List<Int>
    private lateinit var lotto: Lotto
    private val numberGenerator = NumberGenerator()

    @BeforeEach
    fun setUp() {
        val randomNumbers = numberGenerator.makeRandomNumbers()
        lottoNumbers = numberGenerator.drawSixNumbers(randomNumbers)
        lotto = Lotto(lottoNumbers)
    }

    @Test
    fun `로또 번호가 1에서 45사이의 숫자인지 확인`() {
        assertThat(lottoNumbers.all { number ->
            number in 1..45
        }).isTrue
    }

    @Test
    fun `로또 번호가 중복되지 않는지 확인`() {
        val actual = lottoNumbers.size
        val expected = lottoNumbers.toSet().size

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 번호가 6개인지 확인`() {
        val actual = lottoNumbers.size

        assertThat(actual).isEqualTo(6)
    }

    @Test
    fun `로또 번호가 오름차순인지 확인`() {
        assertThat(lottoNumbers).isEqualTo(lottoNumbers.sorted())
    }
}
