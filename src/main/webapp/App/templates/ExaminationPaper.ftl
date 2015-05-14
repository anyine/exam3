<?xml version="1.0" encoding="UTF-8" ?>
<exam title="${exam.title}">
<#list exam.questionGroups as que>
	<question-group type="${que.type}" remark="${que.remark}"
		count="${que.count}" point="${que.point}">
		<#list que.questions as q>
		<question content="${q.content}">
			<#if (q.options)??>
			<#list q.options as option>
			<option>${option}</option>
			</#list>
			</#if>
			<#if (q.answer)??>
			<answer>${q.answer}</answer>
			</#if>
		</question>
		</#list>
	</question-group>
</#list>
</exam>