package com.jstarcraft.recommendation.evaluator.ranking;

import java.util.Collection;
import java.util.List;

import com.jstarcraft.ai.utility.Int2FloatKeyValue;
import com.jstarcraft.recommendation.evaluator.RankingEvaluator;

/**
 * 平均准确率均值评估器
 * 
 * <pre>
 * MAP = Mean Average Precision
 * https://en.wikipedia.org/wiki/Information_retrieval
 * https://www.kaggle.com/wiki/MeanAveragePrecision
 * </pre>
 * 
 * @author Birdy
 */
public class MAPEvaluator extends RankingEvaluator {

	public MAPEvaluator(int size) {
		super(size);
	}

	@Override
	protected float measure(Collection<Integer> checkCollection, List<Int2FloatKeyValue> recommendList) {
		if (recommendList.size() > size) {
			recommendList = recommendList.subList(0, size);
		}
		int count = 0;
		float map = 0F;
		for (int index = 0; index < recommendList.size(); index++) {
			int key = recommendList.get(index).getKey();
			if (checkCollection.contains(key)) {
				count++;
				map += 1F * count / (index + 1);
			}
		}
		return map / (checkCollection.size() < recommendList.size() ? checkCollection.size() : recommendList.size());
	}

}
