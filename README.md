# Agentic AI with Quarkus

| Agentic Systems in a nutshell | 
|---| 
| Although there is no universally agreed definition of what an AI agent is, in practice several patterns are emerging that demonstrate how to coordinate and combine the capabilities of multiple AI services, in order to create AI-based systems that can accomplish more complex tasks. <br/><br/> According to a [recent article published by Antropic researchers](https://www.anthropic.com/research/building-effective-agents), these Agentic Systems architectures can be grouped in 2 main categories: **workflows**, where LLMs and tools are orchestrated through predefined code paths, and **agents**, where LLMs dynamically direct their own processes and tool usage, maintaining control over how they execute tasks. |


## Implementing agentic AI patterns with Quarkus

The idea of this project is discussing the most common agentic AI patterns and architectures, showing for each of them, with a very practical example, how they can be implemented through Quarkus and its LangChain4j integration.  

## Prompt chaining

Prompt chaining is probably the simplest, yet powerful and effective, technique in agentic AI workflows where the output of one prompt becomes the input for the next, enabling complex, multi-step reasoning or task execution. It is ideal for situations where there is a clear way to decompose a complex task, in smaller and better delimited parts, thus also reducing the possibilities of hallucinations or other LLMs misbehaving. 

![](images/chaining-pattern.png)

A typical use case where to apply this technique is content creation, like advertising, or novel writing. For instance, this [first example](https://github.com/mariofusco/quarkus-agentic-ai/blob/main/src/main/java/org/agenticai/promptchaining) leverages this patterns to implement a creative writing and editing workflow, where a [first AI service](https://github.com/mariofusco/quarkus-agentic-ai/blob/main/src/main/java/org/agenticai/promptchaining/CreativeWriter.java) generates a draft of a story on a topic provided by the user, while subsequently a [second one](https://github.com/mariofusco/quarkus-agentic-ai/blob/main/src/main/java/org/agenticai/promptchaining/StyleEditor.java) rewrites the draft to make it more coherent with a determined style, and a [third one](https://github.com/mariofusco/quarkus-agentic-ai/blob/main/src/main/java/org/agenticai/promptchaining/AudienceEditor.java) execute a final edit to make it a good fit for the required audience.

In this case it is pretty straightforward to expose this service through a [rest endpoint](https://github.com/mariofusco/quarkus-agentic-ai/blob/main/src/main/java/org/agenticai/promptchaining/AudienceEditor.java) that simply invokes these AI services one after the other, making the editors to  rewrite or refine the content produced by the first creative writer.

The rest endpoint allows to define the topic, style and audience of the novel to be produced, so for example it would be possible to obtain a drama about dogs having kids as target audience calling this URL:

http://localhost:8080/write/topic/dogs/style/drama/audience/kids

thus generating a result like [this](https://github.com/mariofusco/quarkus-agentic-ai/blob/main/text/dogs-novel.txt). Since this project integrates out-of-the-box the observability capabilities provided by Quarkus, it is also possible to give a look at the tracing of the flow of invocations performed to fulfill this request that of course put in evidence the sequential nature of this pattern.  

![](images/chaining-trace.png)

## Parallelization

![](images/parallel-pattern.png)

http://localhost:8080/evening/mood/romantic

[EveningPlan[movie=1. The Notebook, meal=1. Candlelit Chicken Piccata], EveningPlan[movie=2. La La Land, meal=2. Rose Petal Risotto], EveningPlan[movie=3. Crazy, Stupid, Love., meal=3. Sunset Seared Scallops]]

![](images/parallel-trace.png)

