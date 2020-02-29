package siyi.game.service.impl.gamelevel;

import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.bo.gamelevel.AnswerTianzi;
import siyi.game.bo.gamelevel.CandidateWordTianzi;
import siyi.game.bo.gamelevel.GameLevel;
import siyi.game.bo.gamelevel.QuestionTianzi;
import siyi.game.dao.QuTianziMapper;
import siyi.game.dao.entity.QuTianzi;
import siyi.game.service.gamelevel.GameLevelService;
import siyi.game.utill.RandomGeneratorWord;

import java.lang.reflect.Field;
@Service
public class GameLevelImpl implements GameLevelService {
    @Autowired
    QuTianziMapper quTianziMapper;

    @Override
    public GameLevel queryGameLevelInfo(String userId) {
        /* 选择题型 */

        /* 读取题目和答案 */
        GameLevel gameLevel = new GameLevel();
        QuTianzi quTianzi = new QuTianzi();
        quTianzi.setQuId("Q_tianzi_1");
        quTianzi = quTianziMapper.selectOne(quTianzi);
        // 拆分题目和答案
        QuestionTianzi question = new QuestionTianzi();
        CandidateWordTianzi candidate = new CandidateWordTianzi();
        AnswerTianzi answer = new AnswerTianzi();
        BeanCopier copier = BeanCopier.create(quTianzi.getClass(), question.getClass(), false);
        copier.copy(quTianzi, question, null);
        BeanCopier copier2 = BeanCopier.create(quTianzi.getClass(), candidate.getClass(), false);
        copier2.copy(quTianzi, candidate, null);
        BeanCopier copier3 = BeanCopier.create(candidate.getClass(), answer.getClass(), false);
        copier3.copy(candidate, answer, null);
        gameLevel.setCandidate((CandidateWordTianzi)padWord(candidate)); // 补充候选矩阵
        gameLevel.setQuestionTianzi(question);
        gameLevel.setAnswerTianzi(answer);
        gameLevel.setLevel("1");

        return gameLevel;
    }

    /**
     * 补全待选矩阵：不足10，补到10;大于10小于20，补到20
     * @param answer
     * @return
     */
    private Object padWord(Object answer) {
        Class<?> c = answer.getClass();
        Field[] fields = c.getDeclaredFields();
        int notNUllCount = 0;
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                if (fields[i].get(answer) != null && !"radom".equals(fields[i].get(answer).toString()))
                    notNUllCount++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int index =0;
        if (notNUllCount < 10) {
            index = 10;
        } else if (notNUllCount > 10 && notNUllCount < 20){
            index = 20;
        }
        for (int j=notNUllCount; j <= index; j++){
            try {
                fields[j].setAccessible(true);
                fields[j].set(answer, RandomGeneratorWord.getRandomChar());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return answer;
    }
}
