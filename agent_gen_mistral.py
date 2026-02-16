from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_mistralai import ChatMistralAI
from langchain_core.messages import SystemMessage
import os
from langchain_core.messages import HumanMessage


os.environ["HTTPX_FORCE_IPV4"] = "1"


SYSTEM_PROMPT = SystemMessage(
    content="""
You are a Movie & Series Recommendation Agent.

Your task:
- Recommend movies or TV series based on the user's requested genre or type.
- Always format output strictly as:

Movie/Series Name - Rating - Number of Episodes (or "Movie") - Genre

Rules:
- Use real, well-known titles
- Ratings should be realistic (IMDB-style)
- Be concise
- No extra explanation
"""
)



# API KEYS

GEMINI_KEY = "api_key"
MISTRAL_KEY = "api_key"

#-----------

def get_llm(name: str):
    if name == "gemini":
        return ChatGoogleGenerativeAI(
            model="gemini-2.5-flash",
            temperature=0,
            google_api_key=GEMINI_KEY
        )
    elif name == "mistral":
        return ChatMistralAI(
            model="mistral-medium-latest",
            temperature=0,
            mistral_api_key=MISTRAL_KEY
        )
    else:
        raise ValueError("Unknown LLM")


llm = get_llm("mistral")  # or "gemini"


print("🎬 Movie & Series Recommender Agent")
print("Type your request (or type 'exit' / 'quit' to stop)")

while True:
    user_query = input("\nYou → ").strip()

    if user_query.lower() in {"exit", "quit"}:
        print("Exiting Movie Recommender")
        break

    messages = [
        SYSTEM_PROMPT,
        HumanMessage(content=user_query)
    ]

    response = llm.invoke(messages)

    print("\nRecommendations:\n")
    print(response.content)

