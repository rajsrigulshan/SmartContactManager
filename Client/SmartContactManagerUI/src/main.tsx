import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { Provider } from 'react-redux'
import { store } from './store/store.ts'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import SCMHome from './pages/home/SCMHome.tsx'
import SCMAbout from './pages/about/SCMAbout.tsx'
import Login from './pages/Login.tsx'
import Signup from './pages/Signup.tsx'
import UserDashBoardPage from './pages/user/UserDashBoardPage.tsx'

const router=createBrowserRouter([
      {
        path:"/",
        element:<App/>,
        children:[
          {
            path:"/",
            element:<SCMHome/>
          },
          {
            path:"/about",
            element:<SCMAbout/>
          },
          {
            path:"/services"
            // componnet to be loaded....
          },
          {
            path:"/contact"
            // componnet to be loaded....
          },
          {
            path:"/login",
            element:<Login/>
          },
          {
            path:"/signup",
            element:<Signup/>
          },
          {
            path:"/user",
            element:<UserDashBoardPage/>
          }
        ]
      }
])




createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider store={store}>
      <RouterProvider router={router}/>
    </Provider>
  </StrictMode>,
)
