// import React from 'react'
// import { RotatingLines } from 'react-loader-spinner'

// function Loader() {
//   return (
//     <div className="flex justify-center items-center w-full h-[450px]">
//         <div className="flex flex-col items-center gap-1">
//         <RotatingLines
//             visible={true}
//             height="65"
//             width="65"
//             color="red"
//             strokeWidth="5"
//             animationDuration="0.75"
//             ariaLabel="rotating-lines-loading"
//             wrapperStyle={{}}
//             wrapperClass=""
//             />
//         </div>
//     </div>
//   )
// }

// export default Loader



import React from 'react'
import { ClipLoader } from 'react-spinners'

function Loader() {
  return (
    <div className="flex justify-center items-center w-full ">
      <div className="flex flex-col items-center gap-1">
        <ClipLoader
          color="red"
          loading={true}
          size={45}
          aria-label="clip-loader"
        />
      </div>
    </div>
  )
}

export default Loader
