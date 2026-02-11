import os
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.messages import SystemMessage, HumanMessage


os.environ["GOOGLE_API_KEY"] = "AIzaSyAR0PxEWfEqct4AVxkXLfdr79ARMJp4otM"

model = ChatGoogleGenerativeAI(
    model="gemini-2.5-flash",
    temperature=0.2,
    streaming=True
)

system_prompt = SystemMessage(
    content="""
You are an elite anime recommendation engine built for hardcore anime enthusiasts.

Rules:
- Recommend anime with deep understanding of plot, themes, character arcs, and world-building.
- Give a COMPLETE story overview but avoid major spoilers unless explicitly teteasked.
- Explain:
  • Core plot
  • Genre & themes
  • Why the anime is special
  • Who should watch it
- Use clear sections and clean formatting.
- Suggest 3–5 similar anime with short reasons.
- Be accurate, passionate, and knowledgeable like MyAnimeList + Google combined.
- If the user gives mood or genre, personalize recommendations.
"""
)

while True:
    user_input = input("\n🎌 Ask for anime (or type exit): ")
    if user_input.lower() == "exit":
        break

    messages = [
        system_prompt,
        HumanMessage(content=user_input)
    ]

    for chunk in model.stream(messages):
        print(chunk.content, end="", flush=True)
