import model.Lotto
import model.LottoGenerator
import model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import util.Constant

class PublishLottoTest {
    private lateinit var lotto: Lotto
    private lateinit var lottoNumbers: List<LottoNumber>

    @BeforeEach
    fun setUp() {
        lotto = LottoGenerator.generateLotto()
        lottoNumbers = lotto.getNumbers()
    }

    @Test
    fun `로또 번호가 1에서 45사이의 숫자인지 확인`() {
        assertThat(
            lottoNumbers.all { number ->
                number.getNumber() in Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE
            },
        ).isTrue
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

        assertThat(actual).isEqualTo(Constant.LOTTO_SIZE)
    }

    @Test
    fun `로또 번호가 오름차순인지 확인`() {
        val lottoNumbers = lottoNumbers.map { it.getNumber() }
        assertThat(lottoNumbers).isEqualTo(lottoNumbers.sorted())
    }
}
